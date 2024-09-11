package com.example.multimodule.presention.components

import Diary
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.example.multimodule.model.Mood
import com.example.multimodule.ui.theme.Elevation
import java.time.Instant
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import com.example.multimodule.util.toInstant


@Composable
fun DiaryHolder(diary: Diary, onclick: (String) -> Unit) {

    val localDensity = LocalDensity.current
    var componentHeight by remember {
        mutableStateOf(0.dp)
    }

    Row(modifier = Modifier.clickable(
        indication = null,
        interactionSource = remember {
            MutableInteractionSource()
        }) {
        onclick(diary._id.toString())
    })

    {
        Spacer(modifier = Modifier.width(14.dp))

        Surface(
            modifier = Modifier
                .width(2.dp)
                .height(componentHeight + 14.dp),
            tonalElevation = Elevation.Level1
        ) {


        }

        Spacer(modifier = Modifier.width(14.dp))

        Surface(
            modifier = Modifier.clip(shape = Shapes().medium)
                .onGloballyPositioned {
                    componentHeight = with(localDensity) {
                        it.size.height.dp
                    }
                },
            tonalElevation = Elevation.Level1
        ) {

            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                DiaryHeader(moodName = diary.mood, time = diary.date.toInstant())

                Text(
                    modifier = Modifier.padding(14.dp),
                    text = diary.descripation,
                    style = TextStyle(fontSize = MaterialTheme.typography.titleLarge.fontSize),
                    maxLines = 4
                )
            }

        }

    }
}


    @Composable
    fun DiaryHeader(moodName: String, time: Instant) {

        var mood by remember {
            mutableStateOf(Mood.valueOf(moodName))
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(mood.containerColor)
                .padding(horizontal = 14.dp, vertical = 7.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {

                Image(
                    modifier = Modifier.size(18.dp),
                    painter = painterResource(id = mood.icon),
                    contentDescription = "mood"
                )

                Spacer(modifier = Modifier.size(7.dp))

                Text(
                    text = mood.name,
                    color = mood.contentColor,
                    style = TextStyle(fontSize = MaterialTheme.typography.titleMedium.fontSize)
                )

            }
        }

    }

@Composable
@Preview
fun  DiaryHolderPreview(){
    DiaryHolder(diary = Diary().apply {
        title="Vaibhav"
        descripation="Warning: SDK processing. This version only understands SDK XML versions up to 3 but an SDK XML file of version 4 was encountered. This can happen if you use versions of Android Studio and the command-line tools that were released at different times.\n" +
                "\n"
        mood=Mood.Smile.name

    }, onclick = {})
}


