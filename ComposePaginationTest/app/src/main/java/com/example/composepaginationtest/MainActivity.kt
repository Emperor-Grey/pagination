package com.example.composepaginationtest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.example.composepaginationtest.data.AnimeData
import com.example.composepaginationtest.ui.theme.ComposePaginationTestTheme
import com.example.paginationtest.viewmodel.KitsuViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val kitsuViewModel by viewModels<KitsuViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposePaginationTestTheme {

                val anime = kitsuViewModel.animes.collectAsLazyPagingItems()

                LazyVerticalGrid(
                    columns = GridCells.Fixed(3),
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                    contentPadding = PaddingValues(8.dp)
                ) {

                    items(anime.itemCount) { index ->
                        val anime = anime[index]

                        anime?.let {
                            AnimeCard(anime = it)
                        }
                    }

                }
            }
        }
    }
}

@Composable
fun AnimeCard(anime: AnimeData) {
    Card {
        SubcomposeAsyncImage(modifier = Modifier.fillMaxSize(),
            model = ImageRequest.Builder(LocalContext.current)
                .data(anime.attributes.posterImage.original).crossfade(enable = true).build(),
            contentDescription = anime.attributes.canonicalTitle,
            contentScale = ContentScale.Crop,
            loading = {
                Box(Modifier.size(125.dp, 170.dp), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            })
    }
}
