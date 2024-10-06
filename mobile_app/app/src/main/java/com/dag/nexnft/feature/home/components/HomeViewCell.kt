package com.dag.nexnft.feature.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.dag.nexnft.base.navcontroller.NavScreen
import com.dag.nexnft.feature.home.data.HomeButton

@Composable
fun HomeViewCell(
    feature: HomeButton,
    navController: NavController
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
            .clip(RoundedCornerShape(16.dp))
            .shadow(elevation = 16.dp)
            .background(MaterialTheme.colorScheme.primary)
            .clickable {
                navController.navigate(feature.link)
            }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(16.dp)),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = feature.title,
                color = MaterialTheme.colorScheme.onPrimary,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(32.dp)
            )
        }
    }
}

@Preview
@Composable
fun HomeViewCellPreview() {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        HomeViewCell(
            feature = HomeButton(NavScreen.Generator.route, "NFT Maker"),
            navController = rememberNavController()
        )
    }
}