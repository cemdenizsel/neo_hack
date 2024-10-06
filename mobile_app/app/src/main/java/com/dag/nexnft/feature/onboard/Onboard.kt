package com.dag.nexnft.feature.onboard

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.datastore.core.DataStore
import androidx.datastore.dataStoreFile
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.dag.nexnft.base.DataStoreImpl
import com.dag.nexnft.base.navcontroller.NavScreen
import org.koin.androidx.compose.koinViewModel
import org.koin.compose.koinInject

@Composable
fun DotIndicator(isSelected: Boolean) {
    Box(
        modifier = Modifier
            .size(if (isSelected) 12.dp else 8.dp)
            .background(
                color = if (isSelected) Color.White else Color.Gray,
                shape = RoundedCornerShape(50)
            )
    )
}

@Composable
fun OnboardView(
    onboardVM: OnboardVM = koinViewModel(),
    navigationController: NavController
) {
    val state = onboardVM.state.collectAsState()
    when (val viewState = state.value) {
        is OnboardVS.Navigate -> {
            navigationController.navigate(viewState.route)
            onboardVM.clearState()
        }

        is OnboardVS.MessageScreen -> {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {

                Image(
                    painter = painterResource(id = viewState.imageResource),
                    contentDescription = "Welcome Image"
                )

                Spacer(modifier = Modifier.height(24.dp))

                Text(
                    text = stringResource(id = viewState.messageResource),
                    style = MaterialTheme.typography.headlineLarge
                )
                Spacer(modifier = Modifier.height(24.dp))

                LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    items(onboardVM.getTotalStatus()) { index ->
                        DotIndicator(isSelected = index == onboardVM.getCurrentIndex())
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                Button(
                    onClick = { onboardVM.goNextStep() },
                    modifier = Modifier
                        .width(200.dp)
                        .height(56.dp),
                    shape = RoundedCornerShape(32.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary
                    )
                ) {
                    Text(text = "Get Start", style = MaterialTheme.typography.bodyMedium)
                }
            }
        }

        else -> {}
    }

}

@Composable
@Preview
fun OnboardViewPreview1() {
    OnboardView(
        navigationController = rememberNavController()
    )
}