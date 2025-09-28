package com.example.kidschatting

data class Message(
    val text: String,
    val isSent: Boolean // true: 사용자가 보낸 메시지, false: 서버에서 받은 메시지
)

