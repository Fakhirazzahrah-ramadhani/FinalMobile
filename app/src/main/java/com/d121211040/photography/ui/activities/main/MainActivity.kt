package com.d121211040.photography.ui.activities.main

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.d121211040.photography.data.models.Photography
import com.d121211040.photography.ui.activities.detail.DetailActivity
import com.d121211040.photography.ui.theme.AplikasiPhotographyTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AplikasiPhotographyTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.secondary
                ) {
                    Column {
                        CenterAlignedTopAppBar(
                            modifier = Modifier.background(MaterialTheme.colorScheme.primary),
                            title = {
                                Text(
                                    text ="PHOTOGRAPHY",
                                    fontWeight = FontWeight.SemiBold,
                                )
                            }
                        )
                        val mainViewModel: MainViewModel = viewModel(factory = MainViewModel.Factory)
                        ListPhotographyScreen(mainViewModel.mainUiState)
                    }
                }
            }
        }
    }
    //
    @Composable
    private fun ListPhotographyScreen(mainUiState: MainUiState, modifier: Modifier = Modifier) {
        Column(
            modifier = modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ){
            when(mainUiState) {
                is MainUiState.Success -> ListPhotography(mainUiState.photography)
                is MainUiState.Error -> Text(text = "Terjadi Error", fontSize = 16.sp)
                is MainUiState.Loading ->  Text(text = "Sedang Loading", fontSize = 16.sp)
            }
        }
    }

    @Composable
    fun ListPhotography(photography: List<Photography>, modifier: Modifier = Modifier) {
        LazyVerticalGrid(modifier = modifier, columns = GridCells.Fixed(2)) {
            items(photography) { photography->
                PhotographyCard(photography = photography)
            }
        }
    }
    @Composable
    fun PhotographyCard(photography: Photography) {
        Card(
            modifier = Modifier
                .padding(16.dp)
                .border(1.dp, Color.Gray, RoundedCornerShape(8.dp))
                .height(280.dp)
                .clickable {
                    val intent = Intent(this, DetailActivity::class.java)
                    intent.putExtra("PHOTOGRAPHY", photography)
                    startActivity(intent)
                }) {
            Column (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)){
                AsyncImage(
                    model = ImageRequest.Builder(context = LocalContext.current)
                        .data(photography.webformatURL)
                        .crossfade(true)
                        .build(),
                    contentDescription = photography.tags,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .clip(MaterialTheme.shapes.medium)

                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(text = "Taken by: ${photography.user.toString()}")
            }
        }
    }

}