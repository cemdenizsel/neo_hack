package com.dag.nexnft.feature.generator.data

enum class MessageSender{
    User,
    System
}

data class Message(
    val text:String,
    val type:MessageSender
)