package com.example.multimodule.navigation

import com.example.multimodule.util.Constant.WRITE_SCREEN_DIARY_ID

sealed class Screen(val route:String) {
     data object Authentication:Screen("authentication_screen")
     data object Home:Screen("home_screen")
     data object Write:Screen("write_screen?$WRITE_SCREEN_DIARY_ID={$WRITE_SCREEN_DIARY_ID}"){
        fun passId(diaryId:String)="write_screen?$WRITE_SCREEN_DIARY_ID=$diaryId"
    }
}