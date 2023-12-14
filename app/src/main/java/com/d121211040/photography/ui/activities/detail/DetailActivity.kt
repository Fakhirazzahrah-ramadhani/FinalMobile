package com.d121211040.photography.ui.activities.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.shapes
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.d121211040.photography.R
import com.d121211040.photography.data.models.Photography
import com.d121211040.photography.ui.theme.AplikasiPhotographyTheme
import kotlinx.serialization.json.JsonNull.content

class DetailActivity : AppCompatActivity() {

    private var selectedPhotography: Photography? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        selectedPhotography = intent.getParcelableExtra("PHOTOGRAPHY")
        setContent {
            AplikasiPhotographyTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    DetailsScreen()
                }
            }
        } //setContentView(R.layout.activity_detail)
    }

    @Composable
    private fun DetailsScreen() {
        LazyColumn {
            item {
                AsyncImage(
                    model = ImageRequest.Builder(context = LocalContext.current)
                        .data(selectedPhotography?.largeImageURL)
                        .crossfade(true)
                        .build(),
                    contentDescription = "ini gambar",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .width(400.dp)
                        .height(600.dp)
                        .clip(MaterialTheme.shapes.medium),
                )
                //title
                Text(text = selectedPhotography?.collections.toString())
                //desc
                Text(text = selectedPhotography?.tags.toString())
                //content
                Text(text = selectedPhotography?.views.toString())
            }
        }
    }


}
