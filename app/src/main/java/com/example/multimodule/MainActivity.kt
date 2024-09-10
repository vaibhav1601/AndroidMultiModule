package com.example.multimodule
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.multimodule.navigation.Screen
import com.example.multimodule.navigation.SetupNavGraph
import com.example.multimodule.ui.theme.AppTheme
import com.example.multimodule.util.Constant
import io.realm.kotlin.mongodb.App

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        WindowCompat.setDecorFitsSystemWindows(window,false)
        enableEdgeToEdge()
        setContent {
            AppTheme  {
                 val navController = rememberNavController()
                SetupNavGraph(
                    startDestination = getStartDestination(),
                    navController = navController
                )

            }
        }


    }

   private fun getStartDestination():String{

       val user= App.create(Constant.APP_ID).currentUser
       return if(user!=null &&  user.loggedIn) Screen.Home.route
       else Screen.Authentication.route

    }
}

