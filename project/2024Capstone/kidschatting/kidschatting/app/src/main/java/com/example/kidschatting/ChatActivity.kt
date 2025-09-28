package com.example.kidschatting

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kidschatting.network.ChatRequest
import com.example.kidschatting.network.ChatResponse
import com.example.kidschatting.network.RetrofitInstance
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File

class ChatActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var messageInput: EditText
    private lateinit var sendButton: Button
    private lateinit var chatAdapter: ChatAdapter
    private val messages = mutableListOf<Message>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        recyclerView = findViewById(R.id.rv_chat)
        messageInput = findViewById(R.id.et_message)
        sendButton = findViewById(R.id.btn_send)

        chatAdapter = ChatAdapter(messages)
        recyclerView.adapter = chatAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        sendButton.setOnClickListener {
            val userMessage = messageInput.text.toString()
            if (userMessage.isNotBlank()) {
                addMessage(userMessage, isSent = true)

                // 텍스트 메시지를 서버로 전송
                sendTextToServer(userMessage)
                messageInput.text.clear()
            } else {
                // 파일 전송의 경우
                val filePath = "android.resource://${packageName}/raw/speak.mp4" // 예시 파일 경로
                sendFileToServer(filePath)
            }
        }
    }

    // 메시지 추가 및 RecyclerView 업데이트
    private fun addMessage(text: String, isSent: Boolean) {
        val message = Message(text, isSent)
        messages.add(message)
        chatAdapter.notifyItemInserted(messages.size - 1)
        recyclerView.scrollToPosition(messages.size - 1)
    }

    // 서버에 텍스트 메시지 전송
    private fun sendTextToServer(message: String) {
        val chatRequest = ChatRequest(message)
        RetrofitInstance.api.sendMessage(chatRequest).enqueue(object : Callback<ChatResponse> {
            override fun onResponse(call: Call<ChatResponse>, response: Response<ChatResponse>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        addMessage(it.response, isSent = false) // 받은 메시지 추가
                    }
                } else {
                    addMessage("Error: ${response.code()}", isSent = false)
                }
            }

            override fun onFailure(call: Call<ChatResponse>, t: Throwable) {
                addMessage("Failed to send message to server: ${t.message}", isSent = false)
            }
        })
    }

    // 서버에 파일 전송
    private fun sendFileToServer(resourcePath: String) {
        try {
            // Raw 리소스 파일 가져오기
            val rawResource = resources.openRawResource(R.raw.speak)
            val tempFile = File(cacheDir, "speak.mp4")

            // InputStream -> File로 복사
            rawResource.use { inputStream ->
                tempFile.outputStream().use { outputStream ->
                    inputStream.copyTo(outputStream)
                }
            }

            if (!tempFile.exists()) {
                Toast.makeText(this, "Failed to prepare file for upload.", Toast.LENGTH_SHORT).show()
                return
            }

            // MultipartBody 준비
            val requestFile = tempFile.asRequestBody("video/mp4".toMediaTypeOrNull())
            val body = MultipartBody.Part.createFormData("file", tempFile.name, requestFile)

            // Retrofit을 통해 서버로 전송
            RetrofitInstance.api.uploadAudio(body).enqueue(object : Callback<ResponseBody> {
                override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                    if (response.isSuccessful) {
                        val responseBody = response.body()?.string() ?: "No response from server"
                        addMessage(responseBody, isSent = false) // 서버 응답 메시지 추가
                    } else {
                        addMessage("Error: ${response.code()}", isSent = false)
                    }
                }

                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    addMessage("Failed to send file to server: ${t.message}", isSent = false)
                }
            })
        } catch (e: Exception) {
            Toast.makeText(this, "Error processing file: ${e.message}", Toast.LENGTH_SHORT).show()
        }
    }
}
