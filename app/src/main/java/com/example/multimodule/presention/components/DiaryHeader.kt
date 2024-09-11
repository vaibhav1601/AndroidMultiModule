package com.example.multimodule.presention.components
import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import java.time.LocalDate

@SuppressLint("DefaultLocale")
@Composable
fun DateHolder(localDate: LocalDate) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Column(horizontalAlignment = Alignment.Start) {
            Text(
                text = String.format("%02d", localDate.dayOfMonth),
                style = TextStyle(
                    fontSize = MaterialTheme.typography.titleLarge.fontSize,
                    fontWeight = FontWeight.Light
                )


            )
            Text(
                text = localDate.dayOfWeek.toString().take(3),
                fontSize = MaterialTheme.typography.titleLarge.fontSize,
                fontWeight = FontWeight.Light
            )

        }


            Spacer(Modifier.width(14.dp))

            Column(horizontalAlignment = Alignment.End) {
                Text(
                    text = localDate.month.toString().lowercase().replaceFirstChar { it.titlecase() },
                    style = TextStyle(
                        fontSize = MaterialTheme.typography.titleLarge.fontSize,
                        fontWeight = FontWeight.Light
                    )


                )
                Text(
                    color = MaterialTheme.colorScheme.surfaceBright.copy(alpha = 0.38f),
                    modifier = Modifier.align(alignment =Alignment.Start),
                    text = "${localDate.year}",
                    fontSize = MaterialTheme.typography.bodySmall.fontSize,
                    fontWeight = FontWeight.Light

                )


            }



    }
}

@Composable
@Preview
fun DateHolderPreview(){
    DateHolder(localDate = LocalDate.now())
}