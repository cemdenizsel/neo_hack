package com.dag.nexnft.feature.splash

import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dag.nexnft.base.DataStoreImpl
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SplashScreenVM(private val dataStoreImpl: DataStoreImpl): ViewModel() {

    private val _state = MutableStateFlow<SplashVS>(SplashVS.StatusReading)
    val state = _state.asStateFlow()

    fun getOnboardStatus(){
        viewModelScope.launch {
            val res = dataStoreImpl.Read(booleanPreferencesKey("onboard")) ?: false
            _state.value = SplashVS.SplashStatus(
                res
            )
        }
    }
}