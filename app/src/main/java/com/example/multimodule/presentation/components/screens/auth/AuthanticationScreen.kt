package com.example.multimodule.presentation.components.screens.auth

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import com.example.multimodule.util.Constant
import com.stevdzasan.messagebar.ContentWithMessageBar
import com.stevdzasan.messagebar.MessageBarState
import com.stevdzasan.onetap.OneTapGoogleButton
import com.stevdzasan.onetap.OneTapSignInState
import com.stevdzasan.onetap.OneTapSignInWithGoogle

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AuthenticationScreen(
    loadingState: Boolean,
    authentication:Boolean,
    onButtonClick: () -> Unit,
    messageBarState: MessageBarState,
    onTapSignGoogle: OneTapSignInState,
    onTokenReceived: (String) -> Unit,
    onDialogDismissed: (String) -> Unit,
    navigateHome:()-> Unit
){
   Scaffold( modifier = Modifier
       .background(MaterialTheme.colorScheme.surface)
       .statusBarsPadding()
       .navigationBarsPadding(),
       content = {

           ContentWithMessageBar(messageBarState = messageBarState) {
               AuthenticationContent(
                   loadingState, onButtonClick
               )
           }

       }
   )

    OneTapSignInWithGoogle(
        state =onTapSignGoogle ,
        clientId = Constant.CLIENT_ID ,
        onTokenIdReceived = {
        token->
        onTokenReceived(token)
        Log.d("API",token)

    }, onDialogDismissed = {
        message->
        onDialogDismissed(message)
        Log.d("API",message)

    },

    )
    LaunchedEffect(key1 = authentication) {
        if(authentication){
            navigateHome()
        }else{
        }
    }
}