package com.example.multimodule.navigation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.multimodule.presentation.components.screens.auth.AuthenticationScreen
import com.example.multimodule.presentation.components.screens.auth.AuthenticationViewModel
import com.example.multimodule.util.Constant
import com.example.multimodule.util.Constant.WRITE_SCREEN_DIARY_ID
import com.stevdzasan.messagebar.rememberMessageBarState
import com.stevdzasan.onetap.rememberOneTapSignInState
import io.realm.kotlin.mongodb.App
import kotlinx.coroutines.launch

@Composable
fun SetupNavGraph(startDestination: String, navController: NavHostController) {
    NavHost(
        startDestination = startDestination,
        navController = navController
    ) {
        authenticationRoute(navigateHome = {
            navController.popBackStack()
            navController.navigate(Screen.Home.route)
        })
        homeRoute()
        write()

    }


}

fun NavGraphBuilder.authenticationRoute(
    navigateHome:()->Unit
) {
    composable(route = Screen.Authentication.route) {
        val onTapSignGoogle = rememberOneTapSignInState()
        val messageBarState = rememberMessageBarState()
        val viewModel: AuthenticationViewModel = viewModel()
        val loadingState by viewModel.loadingState
        val authentication by viewModel.authantication

        AuthenticationScreen(
            loadingState = loadingState,
            authentication=authentication,
            onButtonClick = {
                onTapSignGoogle.open()
                viewModel.setLoading(true)


            },
            messageBarState,
            onTapSignGoogle,
            onTokenReceived = { token ->
                viewModel.signInWithMangoAtlas(tokenId = token,
                    onSuccess = {
                        if (it) {
                            messageBarState.addSuccess("Successfully Authentication")

                        }

                        viewModel.setLoading(false)
                    },
                    onError = {
                        messageBarState.addError(it)
                    }
                )

            },
            onDialogDismissed = { message ->
                messageBarState.addError(Exception(message))
            },
            navigateHome = navigateHome

        )


    }
}

fun NavGraphBuilder.homeRoute() {
    composable(route = Screen.Home.route) {
        val scope= rememberCoroutineScope()
        Column (
            modifier =Modifier.fillMaxHeight(),
            verticalArrangement = Arrangement.Center,

            horizontalAlignment = Alignment.CenterHorizontally)
        { Button(
            onClick =
            {
               scope.launch {
                    App.create(Constant.APP_ID).currentUser?.logOut()
                }
            }
        ) {
            Text(text = "Logout")
        }

        }

    }
}


fun NavGraphBuilder.write() {
    composable(
        route = Screen.Write.route,
        arguments = listOf(navArgument(name = WRITE_SCREEN_DIARY_ID) {
            type = NavType.StringType
            nullable = true
        })

    ) {


    }
}




