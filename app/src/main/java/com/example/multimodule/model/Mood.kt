package com.example.multimodule.model

import androidx.compose.ui.graphics.Color
import com.example.multimodule.R
import com.example.multimodule.ui.theme.surfaceAmber
import com.example.multimodule.ui.theme.surfaceBlue
import com.example.multimodule.ui.theme.surfaceDeepBrown
import com.example.multimodule.ui.theme.surfaceDeepOrange
import com.example.multimodule.ui.theme.surfaceGrey
import com.example.multimodule.ui.theme.surfaceIndigo
import com.example.multimodule.ui.theme.surfacePink
import com.example.multimodule.ui.theme.surfacePurple
import com.example.multimodule.ui.theme.surfaceRed
import com.example.multimodule.ui.theme.surfaceSmile
import com.example.multimodule.ui.theme.surfaceYellow

enum class Mood (
    val icon:Int,
    val contentColor:Color,
    val containerColor: Color
    ){
        Smile(
            icon=R.drawable.ic_smile,
            contentColor = Color.Yellow,
            containerColor =surfaceSmile
        ),
    Angle(
        icon=R.drawable.ic_angel,
        contentColor = Color.Blue,
        containerColor = surfaceBlue
    ),
    Angry(
        icon=R.drawable.ic_angry,
        contentColor = Color.Red,
        containerColor = surfaceRed
    ),
    Anguished(
        icon=R.drawable.ic_anguished,
        contentColor = Color.Gray,
        containerColor = surfaceAmber
    ),
    Devil(
        icon=R.drawable.ic_devil,
        contentColor = Color.Magenta,
        containerColor = surfacePurple
    ),
    Naughty(
        icon=R.drawable.ic_naughty,
        contentColor = Color.White,
        containerColor = surfacePink
    ),
    NotFelling(
    icon=R.drawable.ic_not_feeling_well,
    contentColor = Color.DarkGray,
    containerColor = surfaceGrey
    ),
    Romantic(
        icon=R.drawable.ic_romantic,
        contentColor = Color.Yellow,
        containerColor = surfaceYellow
    ),
    Sleepy(
        icon=R.drawable.ic_sleepy,
        contentColor = Color.Transparent,
        containerColor = surfaceDeepBrown
    ), Surprise(
        icon=R.drawable.ic_surprise_face,
        contentColor = Color.Unspecified,
        containerColor = surfaceDeepOrange
    ),
    Wow(
        icon=R.drawable.ic_wow_emoji,
        contentColor = Color.Transparent,
        containerColor = surfaceIndigo
    )







}

