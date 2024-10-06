package com.dag.nexnft.feature.generator

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dag.nexnft.feature.generator.data.Message
import com.dag.nexnft.feature.generator.data.MessageSender
import com.dag.nexnft.feature.generator.repository.imagegenerator.ApiSourceImageGeneration
import com.dag.nexnft.feature.onboard.OnboardVS
import com.google.ai.client.generativeai.Chat
import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.type.Content
import com.google.ai.client.generativeai.type.ImagePart
import com.google.ai.client.generativeai.type.Part
import com.google.ai.client.generativeai.type.TextPart
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class GeneratorVM(
    private val model: GenerativeModel,
    private val apiSourceImageGeneration: ApiSourceImageGeneration
) : ViewModel() {

    private val _state = MutableStateFlow(GeneratorVS(mutableListOf(), null, ""))
    val state = _state.asStateFlow()

    private lateinit var chat: Chat

    fun startChat() {
        chat = model.startChat(
            history = listOf(
                Content(
                    role = "model",
                    parts = listOf(
                        TextPart(
                            text = "Your duty is to get information from the user about the NFT" +
                                    "he/she wants to create. You have to get the NFT name, the " +
                                    "owner wallet address that NFT will be sent after the creation" +
                                    "and, the description of the image. After the user gives the " +
                                    "description, I will create an image for him/her." +
                                    "When you get all answers from the user please return a value " +
                                    "as json format which keys are the questions and answers are " +
                                    "the values."
                        )
                    )
                )
            )
        )
    }

    private fun callImageApi(message: String) {
        viewModelScope.launch {
            val response = apiSourceImageGeneration.generateImage(message)
            if (response.isSuccessful){
                val image = base64toBitmap(response.body()!!)
                chat.history.add(
                    Content(
                        role = "model",
                        parts = listOf(
                            TextPart(text = "Do you like this image ? Is it similar what you image ?")
                        )
                    )
                )
                _state.value = _state.value.copy(
                    image = image
                )
            }
        }
    }

    fun base64toBitmap(base64String: String): Bitmap {
        val decodedBytes = Base64.decode(base64String, Base64.DEFAULT)
        return BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.size)
    }

    fun respondUserMessage(text: String) {
        viewModelScope.launch {
            val res = chat.sendMessage(text)
            if (text == "OK"){
                _state.value = _state.value.copy(
                    aiResponse = "Nft is being generated",
                    messages = _state.value.messages.plus(Message("Nft is being generated", MessageSender.System))
                        .toMutableList()
                )
            }else{
                res.text?.let { message ->
                    if (message.contains("{") && message.contains("}")) {
                        callImageApi(message)
                        return@launch
                    }

                    _state.value = _state.value.copy(
                        aiResponse = message,
                        messages = _state.value.messages.plus(Message(message, MessageSender.System))
                            .toMutableList()
                    )
                }
            }

        }
    }
}