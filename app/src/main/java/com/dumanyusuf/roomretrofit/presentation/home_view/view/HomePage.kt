package com.dumanyusuf.roomretrofit.presentation.home_view.view

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.dumanyusuf.roomretrofit.Screen
import com.dumanyusuf.roomretrofit.domain.model.Flag
import com.dumanyusuf.roomretrofit.presentation.home_view.HomeViewModel
import com.google.gson.Gson
import java.net.URLEncoder

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomePage(
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val state = viewModel.state.value

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Ülkeler") }
            )
        },
        content = {
            when {
                state.isLoading -> {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp)
                    )
                }

                state.error.isNotEmpty() -> {
                    Text(
                        text = "Hata: ${state.error}",
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        color = Color.Red
                    )
                }

                state.flags.isNotEmpty() -> {
                    LazyColumn(
                        modifier = Modifier
                            .padding(it)
                            .fillMaxSize()
                    ) {
                        items(state.flags) { flag ->
                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(10.dp)
                                    .size(150.dp)
                                    .clickable {
                                        val flagObject = Gson().toJson(flag)
                                        val encodedFlagObject =
                                            URLEncoder.encode(flagObject, "UTF-8")
                                        navController.navigate("${Screen.DetailPageView.route}/$encodedFlagObject")
                                    }
                            ) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Image(
                                        modifier = Modifier.fillMaxWidth(),
                                        contentScale = ContentScale.Crop,
                                        painter = rememberAsyncImagePainter(model = flag.flag),
                                        contentDescription = "Bayrak"
                                    )
                                }
                            }
                        }
                    }
                }

                else -> {
                    Text(
                        text = "Liste boş.",
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        color = Color.Gray
                    )
                }
            }
        }
    )
}
