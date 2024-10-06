package com.dag.nexnft.base.appbar

import androidx.compose.foundation.layout.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.dag.nexnft.base.NextNFTSurfacePreview


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomAppbar() {
    TopAppBar(
        title = {
            Row(
                modifier = Modifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {

            }
        },
        navigationIcon = { },
        actions = {}
    )
}

@Composable
@Preview
fun CustomAppbarPreview() {
    NextNFTSurfacePreview {
        CustomAppbar()
    }
}