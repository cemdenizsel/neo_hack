package com.dag.nexnft.base.common

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import com.dag.nexnft.base.NextNFTSurfacePreview

@Composable
fun NextNFTButton(
    onClick:()->Unit,
    text:String,
    modifier: Modifier = Modifier
){
    Button(
        onClick = onClick,
        shape = RectangleShape,
        colors = ButtonDefaults
            .buttonColors()
            .copy(containerColor = Color.Blue),
        modifier = modifier.fillMaxWidth()
    ) {
        Text(text = text)
    }
}

@Composable
@Preview
fun NextNFTButtonPreview(){
    NextNFTSurfacePreview {
        NextNFTButton(onClick = {  }, text = "Neuladen")
    }
}
