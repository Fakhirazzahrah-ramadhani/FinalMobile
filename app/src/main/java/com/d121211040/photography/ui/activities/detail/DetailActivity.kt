package com.d121211040.photography.ui.activities.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.shapes
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.d121211040.photography.R
import com.d121211040.photography.data.models.Photography
import com.d121211040.photography.ui.theme.AplikasiPhotographyTheme
import kotlinx.serialization.json.JsonNull.content


class DetailActivity : ComponentActivity() {

    private var selectedPhotography: Photography? = null

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        selectedPhotography = intent.getParcelableExtra("PHOTOGRAPHY")
        setContent {
            AplikasiPhotographyTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.onSecondary
                ) {
                    DetailsScreen()
                }
            }
        } //setContentView(R.layout.activity_detail)
    }

    @Composable
    fun DetailsScreen() {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ){
            AsyncImage(
                model = ImageRequest.Builder(context = LocalContext.current)
                    .data(selectedPhotography?.largeImageURL)
                    .crossfade(true)
                    .build(),
                contentDescription = selectedPhotography?.tags,
                modifier = Modifier
                    .padding(16.dp)
                    .border(1.dp, Color.Gray, RoundedCornerShape(8.dp))
                    .width(400.dp)
                    .height(500.dp)
                    .clip(MaterialTheme.shapes.medium),
                contentScale = ContentScale.Crop,

                )
            //Text(text = "Taken by: ${photography.user.toString()}")
            //Detail
            Text(text ="Detail:${selectedPhotography?.tags.toString()}",
                style = MaterialTheme.typography.bodyLarge, fontWeight = FontWeight.Bold )

            Spacer(modifier = Modifier.height(8.dp))
            //Likes
            Row {
                Image(painter = painterResource(id = R.drawable.icons8_heart_48), contentDescription = null)
                Spacer(modifier = Modifier.width(8.dp))
                Text(text =" ${selectedPhotography?.likes.toString()}" )
            }

            Spacer(modifier = Modifier.height(8.dp))
            //content
            Row{
                Image(painter = painterResource(id = R.drawable.icons8_view_64), contentDescription = null )
                Spacer(modifier = Modifier.width(8.dp))
                Text(text ="${selectedPhotography?.views.toString()}" )
            }


        }
    }
}
