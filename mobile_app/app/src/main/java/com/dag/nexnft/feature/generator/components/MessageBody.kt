package com.dag.nexnft.feature.generator.components

import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dag.nexnft.R
import com.dag.nexnft.feature.generator.data.Message
import com.dag.nexnft.feature.generator.data.MessageSender

@Composable
fun MessageUserIcon() {
    Column {
        Spacer(modifier = Modifier.size(16.dp))
        Icon(
            Icons.Default.AccountCircle,
            modifier = Modifier.size(32.dp),
            contentDescription = "Profile",
            tint = Color.White
        )
    }
}

@Composable
fun MessageImageCell(
    modifier: Modifier,
    image: Bitmap? = null
) {
    Row(
        modifier = modifier.fillMaxSize(),
        horizontalArrangement = Arrangement.Start
    ) {
        MessageUserIcon()
        Spacer(modifier = Modifier.size(8.dp))
        Box(
            modifier = Modifier
                .weight(1f)
                .clip(RoundedCornerShape(4.dp))
                .background(Color.White)
                .padding(8.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.cat),
                contentDescription = "contentDescription"
            )
        }
    }
}

@Preview
@Composable
fun MessageImageCellView(){
    MessageImageCell(modifier = Modifier)
}

@Composable
fun MessageCell(
    message: Message,
    modifier: Modifier
) {
    Row(
        modifier = modifier.fillMaxSize(),
        horizontalArrangement = if (message.type == MessageSender.System)
            Arrangement.Start else Arrangement.End
    ) {
        if (message.type == MessageSender.System) {
            MessageUserIcon()
            Spacer(modifier = Modifier.size(8.dp))
        }
        Box(
            modifier = Modifier
                .weight(1f)
                .clip(RoundedCornerShape(4.dp))
                .background(Color.White)
                .padding(8.dp)
        ) {
            Text(
                text = message.text,
                modifier = Modifier
                    .background(Color.White),
                style = MaterialTheme.typography.bodyMedium
                    .copy(color = Color.Black)
            )
        }
        if (message.type == MessageSender.User) {
            Spacer(modifier = Modifier.size(8.dp))
            MessageUserIcon()
        }
    }
}

@Composable
fun MessageBody(
    messages: List<Message>,
    image: Bitmap? = null,
    onSend: (text: String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Box(
            modifier = Modifier
                .weight(1f) // LazyColumn will take available space above GeneratorInput
                .fillMaxSize()
        ) {
            LazyColumn {
                items(messages) { message ->
                    MessageCell(
                        message = message,
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )
                    Spacer(modifier = Modifier.size(16.dp))
                }
                image?.let {
                    item {
                        MessageImageCell(
                            modifier = Modifier.padding(horizontal = 16.dp),
                            image = image
                        )
                    }
                }
            }
        }
        GeneratorInput(
            modifier = Modifier.padding(8.dp),
            onSend = onSend
        )
    }
}

@Preview
@Composable
fun MessageCellSystemPreview() {
    MessageCell(
        modifier = Modifier,
        message = Message("Test", MessageSender.System)
    )
}

@Preview
@Composable
fun MessageCellUserPreview() {
    MessageCell(
        modifier = Modifier,
        message = Message("Test", MessageSender.User)
    )
}

@Preview
@Composable
fun MessageBodyPreview() {
    MessageBody(
        messages = listOf(
            Message("Hi, How is going ?", MessageSender.User),
            Message("Fine bro what about you ? ", MessageSender.System),
            Message("Going well. Tell me 2+2", MessageSender.User),
            Message("4 bro", MessageSender.System),
            Message("Thanks bro", MessageSender.User),
            Message("Hi, How is going ?", MessageSender.User),
            Message("Fine bro what about you ? ", MessageSender.System),
            Message("Going well. Tell me 2+2", MessageSender.User),
            Message("4 bro", MessageSender.System),
            Message("Thanks bro", MessageSender.User),
            Message("Hi, How is going ?", MessageSender.User),
            Message("Fine bro what about you ? ", MessageSender.System),
            Message("Going well. Tell me 2+2", MessageSender.User),
            Message("Going well. Tell me 2+2", MessageSender.User),
        )
    ) {}
}
