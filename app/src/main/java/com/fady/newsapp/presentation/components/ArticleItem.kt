package com.fady.newsapp.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.fady.newsapp.R
import com.fady.newsapp.domain.models.NewsItem
import com.fady.newsapp.presentation.theme.dimen_16
import com.fady.newsapp.presentation.theme.dimen_8
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.coil.CoilImage
import java.time.LocalDateTime
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

@Composable
fun ArticleItem(
    newsItem: NewsItem,
    onAddToFavorites: (NewsItem) -> Unit,
    onRemoveFromFavorites: (NewsItem) -> Unit,
) {
    var isLiked by remember { mutableStateOf(false) }
    val formatter = DateTimeFormatter.ofPattern("MMM dd, yyyy")
    val date = ZonedDateTime.parse(newsItem.publishedAt).toLocalDateTime()

    Card(modifier = Modifier.padding(dimen_8)) {
        Column {
            CoilImage(
                modifier = Modifier
                    .fillMaxWidth(),
                imageOptions = ImageOptions(
                    contentScale = ContentScale.Fit,
                    alignment = Alignment.Center
                ),
                imageModel = { newsItem.imageUrl },
                loading = {
                    Image(
                        modifier = Modifier.fillMaxWidth(),
                        painter = painterResource(id = R.drawable.placeholder),
                        contentDescription = null
                    )
                },
                failure = {
                    Image(
                        modifier = Modifier.fillMaxWidth(),
                        painter = painterResource(id = R.drawable.placeholder),
                        contentDescription = null
                    )
                }
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = newsItem.title,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
                    modifier = Modifier
                        .weight(1f)
                        .padding(dimen_16)
                )
                IconButton(onClick = {
                    isLiked = !isLiked
                    if (isLiked) {
                        onAddToFavorites(newsItem)
                    } else {
                        onRemoveFromFavorites(newsItem)
                    }
                }) {
                    Icon(
                        imageVector = if (isLiked) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
                        contentDescription = "Like",
                        tint = if (isLiked) Color.Red else Color.Gray
                    )
                }
            }
            Text(
                text = stringResource(id = R.string.by_author, newsItem.author),
                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
                modifier = Modifier.padding(start = dimen_16, end = dimen_16)
            )
            Text(
                text = newsItem.description,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(start = dimen_16, end = dimen_16)
            )
            Text(
                text = stringResource(id = R.string.published_at, date.format(formatter)),
                style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Bold),
                modifier = Modifier.padding(start = dimen_16, end = dimen_16, bottom = dimen_16)

            )
        }
    }

}


@Preview
@Composable
private fun ArticlePreview() {
    ArticleItem(newsItem = NewsItem(
        author = "Fady",
        description = "This is a description",
        publishedAt = LocalDateTime.now().toString(),
        title = "This is a title",
        imageUrl = "https://www.example.com"
    ), {}, {})
}