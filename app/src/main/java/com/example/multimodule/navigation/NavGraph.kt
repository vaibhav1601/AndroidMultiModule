package com.example.multimodule.navigation

import androidx.compose.material3.DrawerValue
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.multimodule.presention.screens.auth.AuthenticationScreen
import com.example.multimodule.presention.screens.auth.AuthenticationViewModel
import com.example.multimodule.presention.components.DisplayAlertDialogs
import com.example.multimodule.presention.screens.home.HomeScreen
import com.example.multimodule.repository.MangoDB
import com.example.multimodule.util.Constant
import com.example.multimodule.util.Constant.WRITE_SCREEN_DIARY_ID
import com.stevdzasan.messagebar.rememberMessageBarState
import com.stevdzasan.onetap.rememberOneTapSignInState
import io.realm.kotlin.mongodb.App
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

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
        homeRoute(navigateToWrite = {
            navController.navigate(Screen.Write.route)

        },
            navigateAuthentication = {
                navController.popBackStack()
                navController.navigate(Screen.Authentication.route  )
            }

            )
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
                        messageBarState.addSuccess("Successfully Authentication")
                    },
                    onError = {
                        messageBarState.addError(it)
                    }

                )

                viewModel.setLoading(false)


            },
            onDialogDismissed = { message ->
                messageBarState.addError(Exception(message))
            },
            navigateHome = navigateHome

        )


    }
}

fun NavGraphBuilder.homeRoute(
    navigateToWrite:()->Unit,
    navigateAuthentication:()->Unit
) {
    composable(route = Screen.Home.route) {
        val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
        var signOutDialogOpened by remember {
            mutableStateOf(false)
        }
        val scope= rememberCoroutineScope()
        HomeScreen(
            drawerState,
            onMenuClick = {
                scope.launch {
                    drawerState.open()

                }
            },
            onSignOutClicked = {
                signOutDialogOpened=true
            },
            navigateToWrite = navigateToWrite,

        )


        LaunchedEffect(key1 = Unit) {
            MangoDB.configureTheRealm()
        }

        DisplayAlertDialogs(
            title = "Sign out",
            message = "are you sure you want to sign out from your google account ",
            dialogOpened =signOutDialogOpened,
            onCloseDialog = {signOutDialogOpened=false} ,
            onYesClicked = {
                scope.launch(Dispatchers.IO) {
                    val user= App.create(Constant.APP_ID).
                            currentUser
                    if(user!=null){
                        user.logOut()
                        withContext(Dispatchers.Main){
                            navigateAuthentication()

                        }
                    }

                }
            },

        )

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





