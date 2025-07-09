package com.mahgoub.caffeine

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.mahgoub.caffeine.ui.navigation.CaffeineApp
import com.mahgoub.caffeine.ui.theme.CaffeineTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
//            CompositionLocalProvider(
//                LocalNavigationProvider provides rememberNavController()
//            ) {
//            }
            CaffeineTheme {
                CaffeineApp()
            }
        }
    }
}

//val LocalNavigationProvider = staticCompositionLocalOf<NavHostController> {
//    error("No navigation host controller provided.")
//}
