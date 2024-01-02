package com.dogbreedapp.uiscreen

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberImagePainter
import com.dogbreedapp.viewmodel.SearchViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun SearchScreen(viewModel: SearchViewModel = hiltViewModel()) {

    val query: MutableState<String> = remember { mutableStateOf("") }
    val result = viewModel.list.value
    Surface(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.padding(8.dp)) {

            OutlinedTextField(value = query.value, onValueChange = {
                query.value = it
                viewModel.getBreedList(query.value)

            }, enabled = true,
                singleLine = true,
                leadingIcon = {
                    Icon(imageVector = Icons.Default.Search, contentDescription = null)
                },
                label = { Text(text = "Search") },
                modifier=Modifier.fillMaxWidth()
            )




            if (result.isLoading) {
                Log.d("TAG", "MainContent: in the loading")
                Box(modifier = Modifier
                    .fillMaxSize()) {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
            }


            if (result.error.isNotBlank()) {
                Log.d("TAG", "MainContent: ${result.error}")
                Box(modifier = Modifier
                    .fillMaxSize()) {
                    Text(
                        modifier = Modifier.align(Alignment.Center),
                        text = viewModel.list.value.error
                    )
                }
            }

            if(result.error.isBlank()&&result.data.isNullOrEmpty()){
                    Box(modifier = Modifier.fillMaxSize()) {
                        Column(modifier = Modifier.align(Alignment.Center)) {

                            Text(textAlign = TextAlign.Center,
                                text = ("Welcome to Dog Breed App"),
                                fontWeight = FontWeight.Bold
                            )

                            Text(text = "hound",
                                textAlign = TextAlign.Center,
                                    modifier = Modifier.clickable
                                    { viewModel.getBreedList("hound") }.padding(
                                        8.dp,
                                        8.dp,
                                        8.dp,
                                        8.dp
                                    ),
                                color = Color.Blue)

                            Text(text = "african",
                                textAlign = TextAlign.Center,
                                modifier = Modifier.clickable
                                { viewModel.getBreedList("african") }.padding(
                                    8.dp,
                                    8.dp,
                                    8.dp,
                                    8.dp
                                ),
                                color = Color.Blue)

                            Text(text = "husky",
                                textAlign = TextAlign.Center,
                                modifier = Modifier.clickable
                                { viewModel.getBreedList("husky") }.padding(
                                    8.dp,
                                    8.dp,
                                    8.dp,
                                    8.dp
                                ),
                                color = Color.Blue)

                            Text(text = "beagle",
                                textAlign = TextAlign.Center,
                                modifier = Modifier.clickable
                                { viewModel.getBreedList("beagle") }.padding(
                                    8.dp,
                                    8.dp,
                                    8.dp,
                                    8.dp
                                ),
                                color = Color.Blue)
                        }
                    }
            }


            if (result.data?.isNotEmpty() == true) {
                LazyVerticalGrid(columns = GridCells.Fixed(2)) {
                    Log.d("TAG", "MainContent: Your Token")
                    viewModel.list.value.data?.let { it ->
                        items(it) {
                            MainContentItem(it)
                        }
                    }

                }

            }



        }
    }


}

@Composable
fun MainContentItem(breed: String) {

    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .height(200.dp)
    ) {
        Image(
            painter = rememberImagePainter(data = breed),
            contentScale = ContentScale.Crop,
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
        )
    }


}