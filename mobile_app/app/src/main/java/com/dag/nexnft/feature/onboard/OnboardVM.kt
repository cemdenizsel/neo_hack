package com.dag.nexnft.feature.onboard

import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dag.nexnft.R
import com.dag.nexnft.base.DataStoreImpl
import com.dag.nexnft.base.navcontroller.NavScreen
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class OnboardVM(private val dataStoreImpl: DataStoreImpl ): ViewModel() {

    private val statusOrder = listOf(OnboardVS.MessageScreen(R.string.message_screen_1, R.drawable.ills),
        OnboardVS.MessageScreen(R.string.message_screen_2,R.drawable.illus),
        OnboardVS.MessageScreen(R.string.message_screen_3,R.drawable.last))
    private var statusArrayIndex = 0

    private val _state = MutableStateFlow<OnboardVS>(statusOrder[statusArrayIndex])
    val state = _state.asStateFlow()

    fun goNextStep(){
        statusArrayIndex += 1
        if (statusArrayIndex == statusOrder.size){
            _state.value = OnboardVS.Navigate(NavScreen.HomeScreen.route)
            return
        }
        _state.value = statusOrder[statusArrayIndex]
    }

    fun getTotalStatus():Int {
        return statusOrder.size
    }

    fun clearState(){
        viewModelScope.launch {
            dataStoreImpl.Save(booleanPreferencesKey("onboard"),true)
        }
        _state.value = OnboardVS.Finished
    }

    fun getCurrentIndex():Int {
        return statusArrayIndex
    }
}