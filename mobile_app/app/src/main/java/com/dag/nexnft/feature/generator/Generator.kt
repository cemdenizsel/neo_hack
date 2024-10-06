package com.dag.nexnft.feature.generator

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import com.dag.nexnft.feature.generator.components.MessageBody
import com.dag.nexnft.feature.generator.data.Message
import com.dag.nexnft.feature.generator.data.MessageSender
import org.koin.androidx.compose.koinViewModel

@Composable
fun Generator(
    generatorVM: GeneratorVM = koinViewModel()
){
    val state = generatorVM.state.collectAsState()
    LaunchedEffect(key1 = Unit) {
        generatorVM.startChat()
    }
    Column {
        MessageBody(messages = state.value.messages, image = state.value.image){ text->
            state.value.messages.add(Message(text,MessageSender.User))
            generatorVM.respondUserMessage(text)
        }
    }
}