package com.example.mycompose

import android.app.Activity
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.navigation.NavGraph
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mycompose.ui.createnotification.CreateNotificationFragment
import com.example.mycompose.ui.history.HistoryFragment
import com.example.mycompose.ui.theme.MyComposeTheme

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.main_activity)
        setContent {
            MyComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    Column {
                        Toolbar()
                    }
                }
            }
        }
        createNotificationChannel()
    }

    @Composable
    fun AppNavigator() {
        val navController = rememberNavController()

        navController.navigate(R.id.historyFragment)
        NavHost(navController = navController, startDestination = "profile") {
            composable("profile") { CreateNotification() }
            composable("friendslist") { HistoryFragment }
        }
    }

    private fun createNotificationChannel() {
        // Create the NotificationChannel, API 26+
        val name = "CHANNEL_NAME"//getString(R.string.channel_name)
        val descriptionText = "CHANNEL_DESCRIPTION"//getString(R.string.channel_description)
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val channel = NotificationChannel(CreateNotificationFragment.CHANNEL_ID, name, importance).apply {
            description = descriptionText
        }
        // Register the channel with the system
        val notificationManager = this.getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }
}


@Composable
fun Toolbar() {
    TopAppBar(
        title = { Text(text = "My app") },
        backgroundColor = MaterialTheme.colors.surface,
        navigationIcon = {
            IconButton(
                onClick = {
//                    result.value = "Drawer icon clicked"
                }
            ) {
                Icon(Icons.Filled.Menu, contentDescription = "")
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyComposeTheme {
        Surface(color = MaterialTheme.colors.background) {
            Column() {
                Toolbar()
            }
        }
    }
}