package com.example.mycompose.ui.createnotification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.OutlinedButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.fragment.app.viewModels
import com.example.mycompose.R
import com.example.mycompose.ui.theme.MyComposeTheme

class CreateNotificationFragment : Fragment() {

    companion object {
        fun newInstance() = CreateNotificationFragment()
        const val CHANNEL_ID = "34"
    }

    private val viewModel: CreateNotificationViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                CreateNotification()
            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    public fun CreateNotification() {
        MyComposeTheme() {

            val appName = viewModel.appName
            val body = viewModel.body

            Column() {
                TextField(
                    value = appName.value,
                    onValueChange = {
                        viewModel.onAppNameChange(it)
                    }
                )
                Spacer(modifier = Modifier.height(10.dp))
                TextField(
                    value = body.value,
                    onValueChange = {
                        viewModel.onBodyChange(it)
                    }
                )
                OutlinedButton(onClick = { createNotification() }) {
                    Text("Create")
                }
            }
        }
    }

    private fun createNotification() {
        val builder = NotificationCompat.Builder(requireContext(), CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_launcher_background)
            .setContentTitle(viewModel.appName.value)
            .setContentText(viewModel.body.value)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setAutoCancel(true)

        with(NotificationManagerCompat.from(requireContext())) {
            // notificationId is a unique int for each notification that you must define
            notify(2, builder.build())
        }
    }


}