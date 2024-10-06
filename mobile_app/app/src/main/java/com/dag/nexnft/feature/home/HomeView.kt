package com.dag.nexnft.feature.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.dag.nexnft.base.navcontroller.NavScreen
import com.dag.nexnft.feature.home.components.HomeViewCell
import com.dag.nexnft.feature.home.data.HomeButton

@Composable
fun HomeView(
    navController: NavController
) {
    val features = listOf(HomeButton(NavScreen.Generator.route,"NFT Maker"))
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .clip(RoundedCornerShape(16.dp))
    ) {
        Column {
            Text(
                text = "Welcome to NexArb!",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.headlineLarge,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.size(32.dp))
            Text(
                text = "Let's explore the blockchain world.",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.headlineMedium
            )
            LazyVerticalGrid(
                columns = GridCells.Adaptive(minSize = 128.dp),
                contentPadding = PaddingValues(horizontal = 8.dp, vertical = 32.dp),
            ) {
                items(features) { feature ->
                    HomeViewCell(feature = feature, navController = navController)
                }
            }
        }

    }
}

@Composable
@Preview
fun HomeViewPreview(){
    HomeView(navController = rememberNavController())
}