package com.example.mycompose.ui.history

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.mycompose.Notification
import com.example.mycompose.R
import com.example.mycompose.ui.theme.MyComposeTheme

class HistoryFragment : Fragment() {

    companion object {
        fun newInstance() = HistoryFragment()
    }

    private lateinit var viewModel: HistoryViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                        NotifList(notifications = listOf(
                            Notification("PayPal", "You received 500 EUR."),
                            Notification("PayPal", "You received 700 EUR."),
                            Notification("PayPal", "You received 100 EUR."))
                        )
            }
        }
    }

}

@Composable
fun NotifList(
    notifications: List<Notification>
) {
    LazyColumn(Modifier.fillMaxWidth()) {
        items(notifications) { notif ->
            NotificationItem(notification = notif)
        }

    }
}

@Composable
fun NotificationItem(
    notification: Notification
) {
    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(5.dp)) {
        Row(modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween) {
            Image(
                painter = painterResource(R.drawable.ic_launcher_background),
                contentDescription = "avatar",
                contentScale = ContentScale.Crop,            // crop the image if it's not a square
                modifier = Modifier
                    .size(34.dp)
                    .clip(CircleShape)                       // clip to the circle shape
                    .border(2.dp, Color.Gray, CircleShape)   // add a border (optional)
                    .align(CenterVertically)
//                    .(10.dp, 0.dp, 0.dp, 0.dp)
            )
            Column(modifier = Modifier.align(CenterVertically)) {
                Text(text = notification.title,
                    modifier = Modifier.padding(15.dp, 0.dp, 0.dp, 0.dp),
                    style = MaterialTheme.typography.body1)
                Text(text = notification.body,
                    modifier = Modifier.padding(15.dp, 0.dp, 0.dp, 0.dp),
                    style = MaterialTheme.typography.caption)
            }
            Row() {
                IconButton(onClick = {}) {
                    Icon(
                        Icons.Filled.Edit,
                        contentDescription = "Edit button",
                    )
                }
                IconButton(onClick = {}) {
                    Icon(
                        Icons.Filled.Refresh,
                        contentDescription = "Refresh button",
                    )
                }
                IconButton(onClick = {}) {
                    Icon(
                        Icons.Filled.Delete,
                        contentDescription = "Delete button",
                        tint = Color.Red
                    )
                }
            }
        }
    }
}
