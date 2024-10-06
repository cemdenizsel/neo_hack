package com.dag.nexnft.base.navcontroller

sealed class NavScreen(val route:String) {
    data object HomeScreen: NavScreen("home_screen")
    data object Onboard: NavScreen("onboard_screen")
    data object Generator: NavScreen("generator")
    data object Splash: NavScreen("splash")

}


fun NavScreen.addData(data:Any):String{
    return this.route.plus(data)
}

fun NavScreen.addPath(path:String):String{
    return this.route.plus("/").plus(path)
}