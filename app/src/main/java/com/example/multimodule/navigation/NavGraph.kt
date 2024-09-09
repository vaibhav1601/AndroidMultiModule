package com.example.multimodule.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.multimodule.presentation.components.screens.auth.AuthenticationScreen
import com.example.multimodule.util.Constant.WRITE_SCREEN_DIARY_ID
import com.stevdzasan.messagebar.rememberMessageBarState
import com.stevdzasan.onetap.rememberOneTapSignInState

@Composable
fun SetupNavGraph(startDestination: String, navController: NavHostController) {
    NavHost(
        startDestination = startDestination,
        navController = navController) {
        authenticationRoute()
        homeRoute()
        write()

    }


}

fun NavGraphBuilder.authenticationRoute(){


  composable(route = Screen.Authentication.route){
      val onTapSignGoogle= rememberOneTapSignInState()
      val messageBarState= rememberMessageBarState()
      AuthenticationScreen(
          loadingState = onTapSignGoogle.opened,
          onButtonClick = {
              onTapSignGoogle.open()

          },
          messageBarState,
          onTapSignGoogle,
         )



  }
}

fun NavGraphBuilder.homeRoute() {
    composable(route = Screen.Home.route) {

    }
}


fun NavGraphBuilder.write() {
    composable(
        route = Screen.Write.route,
        arguments = listOf(navArgument(name = WRITE_SCREEN_DIARY_ID){
            type=NavType.StringType
            nullable=true
        })

    ) {


    }
}




