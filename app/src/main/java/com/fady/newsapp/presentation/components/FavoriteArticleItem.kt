package com.fady.newsapp.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.fady.newsapp.R
import com.fady.newsapp.domain.models.NewsItem
import com.fady.newsapp.presentation.theme.dimen_4
import com.fady.newsapp.presentation.theme.dimen_8
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.coil.CoilImage

@Composable
fun FavoriteArticleItem(
    article: NewsItem, onRemoveFromFavorites: (NewsItem) -> Unit
) {
    Card(
        modifier = Modifier.padding(dimen_8), shape = RoundedCornerShape(dimen_8)
    ) {
        Column {
            CoilImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(
                        shape = RoundedCornerShape(dimen_8)
                    ),
                imageOptions = ImageOptions(
                    contentScale = ContentScale.Fit,
                    alignment = Alignment.Center
                ),
                imageModel = { article.imageUrl },
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

            Spacer(modifier = Modifier.height(dimen_8))
            Text(
                modifier = Modifier.padding(horizontal = dimen_8),
                text = article.title,
                style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold),
                color = Color.Black,
                maxLines = 2,
                fontSize = 16.sp,
                overflow = TextOverflow.Ellipsis,
                lineHeight = 20.sp

            )
            Text(
                text = article.description,
                style = MaterialTheme.typography.bodySmall,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(horizontal = dimen_8, vertical = dimen_4),
            )
            IconToggleButton(
                checked = true,
                onCheckedChange = {
                    onRemoveFromFavorites(article)
                }, modifier = Modifier.align(Alignment.End)
            ) {
                Icon(
                    imageVector = Icons.Filled.Favorite,
                    contentDescription = "Like",
                    tint = Color.Red
                )
            }
        }
    }
}

@Composable
@Preview
fun PreviewFavoriteArticleItem() {
    FavoriteArticleItem(article = NewsItem(
        title = "Title",
        description = "Description",
        imageUrl = "https://picsum.photos/200/300",
        author = "Author",
        publishedAt = "2022-01-01T00:00:00Z"
    ), onRemoveFromFavorites = {})
}
