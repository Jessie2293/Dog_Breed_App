package com.dogbreedapp.uiscreen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.dogbreedapp.ui.theme.DogBreedAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DogBreedAppTheme {
                MyApp {
                    SearchScreen()
                }
            }
        }
    }
}

@Composable
fun MyApp(content: @Composable ()->Unit) {
    content()
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    DogBreedAppTheme {
        MyApp {
            SearchScreen()
        }
    }
}