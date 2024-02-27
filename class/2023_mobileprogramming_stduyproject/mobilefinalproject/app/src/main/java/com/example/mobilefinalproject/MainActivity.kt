package com.example.mobilefinalproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textView.text = ""

        // 버튼을 누르면 쓰레드 동작
        button.setOnClickListener {
            // 쓰레드 생성
            var thread = NetworkThread()
            thread.start()
        }
    }

    // 네트워크를 이용할 때는 쓰레드를 사용해서 접근해야 함
    inner class NetworkThread: Thread(){
        override fun run() {
            // 접속할 페이지 주소: Site
            var site = "https://8oi9s0nnth.apigw.ntruss.com/corona19-masks/v1/storesByAddr/json"
            var url = URL(site)
            var conn = url.openConnection()
            var input = conn.getInputStream()
            var isr = InputStreamReader(input)
            // br: 라인 단위로 데이터를 읽어오기 위해서 만듦
            var br = BufferedReader(isr)

            // Json 문서는 일단 문자열로 데이터를 모두 읽어온 후, Json에 관련된 객체를 만들어서 데이터를 가져옴
            var str: String? = null
            var buf = StringBuffer()

            do{
                str = br.readLine()

                if(str!=null){
                    buf.append(str)
                }
            }while (str!=null)

            // 전체가 객체로 묶여있기 때문에 객체형태로 가져옴
            var root = JSONObject(buf.toString())
            var address: String = root.getString("address")
            var count: Int = root.getInt("count")

            // 화면에 출력
            runOnUiThread {
                textView.append("address: ${address}\n")
                textView.append("count: ${count}\n\n\n")

                // 객체 안에 있는 stores라는 이름의 리스트를 가져옴
                var stores = root.getJSONArray("stores")

                // 리스트에 있는 데이터 중 100개만 읽어옴
                for(i in 0 until 100){
                    var obj2 = stores.getJSONObject(i)

                    var addr: String = obj2.getString("addr")
                    var name: String = obj2.getString("name")
                    var remain_stat: String = obj2.getString("remain_stat")
                    var detail: String? = null

                    if(remain_stat.contentEquals("plenty")){
                        detail = "100개 이상"
                    }else if(remain_stat.contentEquals("some")){
                        detail = "30개 이상 100개 미만"
                    }else if(remain_stat.contentEquals("few")){
                        detail = "2개 이상 30개 미만"
                    }else if(remain_stat.contentEquals("empty")){
                        detail = "1개 이하"
                    }else if(remain_stat.contentEquals("break")){
                        detail = "판매 중지"
                    }

                    // 화면에 출력
                    runOnUiThread {
                        textView.append("판매처 주소: ${addr}\n")
                        textView.append("판매처 이름: ${name}\n")
                        textView.append("재고 상태: ${remain_stat} = $detail\n\n")
                    }
                }
            }
        }
    }
}