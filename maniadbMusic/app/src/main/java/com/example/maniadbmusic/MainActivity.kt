package com.example.maniadbmusic

import java.net.URLEncoder
import ManiaDBService
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.simplexml.SimpleXmlConverterFactory
import retrofit2.Response

class MainActivity : ComponentActivity() {
    private val maniaDBService = Retrofit.Builder()
        .baseUrl("https://www.maniadb.com/")
        .addConverterFactory(SimpleXmlConverterFactory.create())
        .build()
        .create(ManiaDBService::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        val searchText = remember { mutableStateOf(TextFieldValue("")) }
                        SearchBar(searchText)
                        SearchResultSection(searchText)
                    }
                }
            }
        }
    }

    @Composable
    fun SearchBar(searchText: MutableState<TextFieldValue>) {
        TextField(
            value = searchText.value,
            onValueChange = { newText -> searchText.value = newText },
            label = { Text("검색") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            singleLine = true
        )
    }

    @Composable
    fun SearchResultSection(searchText: MutableState<TextFieldValue>) {
        val searchResults = remember { mutableStateListOf<MyArtist>() }
        val coroutineScope = rememberCoroutineScope()

        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "아티스트", style = MaterialTheme.typography.h6)
            searchResults.forEach { artist ->
                Text(text = artist.name)
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(onClick = {
                coroutineScope.launch {
                    val encodedSearchText = URLEncoder.encode(searchText.value.text, "UTF-8")
                    val response: Response<ApiResponse> = maniaDBService.searchArtist(encodedSearchText)
                    if (response.isSuccessful) {
                        searchResults.clear()
                        response.body()?.channel?.artists?.let { xmlArtists ->
                            searchResults.addAll(xmlArtists.map { MyArtist(name = it.title) })
                        }
                    }
                }
            }) {
                Text("검색")
            }

            // 앨범 및 노래 검색 결과 표시 코드 ...
        }
    }
}

data class MyArtist(val name: String)
