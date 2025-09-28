package com.example.kidschatting.network

import okhttp3.MultipartBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

// 요청 및 응답 데이터 클래스
data class ChatRequest(val message: String)
data class ChatResponse(val response: String)

// API 인터페이스
interface ChatApi {
    // 기존 텍스트 메시지 전송
    @POST("chat")
    fun sendMessage(@Body request: ChatRequest): Call<ChatResponse>

    // MP4 파일 업로드
    @Multipart
    @POST("chat")
    fun uploadAudio(@Part file: MultipartBody.Part): Call<ResponseBody>
}
