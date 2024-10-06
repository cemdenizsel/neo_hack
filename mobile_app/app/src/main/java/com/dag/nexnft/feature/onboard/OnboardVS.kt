package com.dag.nexnft.feature.onboard

sealed class OnboardVS {
    class MessageScreen(val messageResource:Int,val imageResource:Int): OnboardVS()
    class Navigate(val route:String): OnboardVS()
    object Finished: OnboardVS()
}
