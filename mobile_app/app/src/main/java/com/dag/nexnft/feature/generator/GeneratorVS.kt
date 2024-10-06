package com.dag.nexnft.feature.generator

import android.graphics.Bitmap
import com.dag.nexnft.feature.generator.data.Message

data class GeneratorVS(
    val messages: MutableList<Message>,
    val image: Bitmap?,
    val aiResponse: String
)