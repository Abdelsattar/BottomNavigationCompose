package com.sattar.mybottomnavigation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.sattar.mybottomnavigation.ui.MainScreen
import com.sattar.mybottomnavigation.ui.theme.MyBottomNavigationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyBottomNavigationTheme {
                MainScreen()
            }
        }
    }
}