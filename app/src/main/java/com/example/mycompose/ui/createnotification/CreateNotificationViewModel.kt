package com.example.mycompose.ui.createnotification

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class CreateNotificationViewModel : ViewModel() {


    val appName = mutableStateOf("App name")
    val body = mutableStateOf("Body text")

    fun onAppNameChange(appName: String) {
        this.appName.value = appName
    }

    fun onBodyChange(body: String) {
        this.body.value = body
    }
}