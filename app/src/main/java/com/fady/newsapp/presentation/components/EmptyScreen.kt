package com.fady.newsapp.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.fady.newsapp.R
import com.fady.newsapp.presentation.theme.dimen_16
import com.fady.newsapp.presentation.theme.dimen_2

@Composable
fun EmptyScreen(
    modifier: Modifier = Modifier, onRetry: () -> Unit = {}
) {
    Box(
        modifier = modifier,
    ) {
        Column {
            Spacer(modifier = Modifier.weight(1f))

            Text(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(id = R.string.error_title_label),
                color = Color.Black,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.weight(1f))
            OutlinedButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(dimen_16),
                onClick = {
                    onRetry()
                },
                colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.Black),
                border = ButtonDefaults.outlinedButtonBorder.copy(width = dimen_2)
            ) {
                Text(
                    text = stringResource(id = R.string.retry), color = Color.Black
                )
            }
        }


    }
}


@Preview
@Composable
fun EmptyScreenPreview() {
    EmptyScreen()
}