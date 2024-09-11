package com.example.multimodule.presention.components
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight

@Composable
fun DisplayAlertDialogs(
    title: String,
    message: String,
    dialogOpened: Boolean,
    onCloseDialog: () -> Unit,
    onYesClicked: () -> Unit

) {
    if (dialogOpened) {
        AlertDialog(
            title = {
                Text(
                    text = title,
                    fontSize = MaterialTheme.typography.headlineSmall.fontSize,
                    fontWeight = FontWeight.Bold
                )
            },
            text = {
                Text(
                    text = message,
                    fontSize = MaterialTheme.typography.titleSmall.fontSize,
                    fontWeight = FontWeight.Medium
                )

            },


            confirmButton = {
                Button(
                    onClick = {
                        onYesClicked()
                    }
                )
                {
                    Text(text = "yes")

                }
            },
            dismissButton = {

                Button(onClick = {
                    onCloseDialog()
                }) {
                    Text(text = "No")
                }

            },
            onDismissRequest = onCloseDialog

        )
    }

}