package com.example.newsapp.presentation.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import com.example.newsapp.R
import com.example.newsapp.presentation.NewsAppViewModel
import com.example.newsapp.presentation.navigation.Routes.CategoryScreen

@Composable
fun HomeScreenUI(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: NewsAppViewModel
) {
    val scrollState = rememberLazyListState()
    val searchTerm = rememberSaveable() { mutableStateOf("") }
//    val selectedCategory = rememberSaveable { mutableStateOf("") }
    val selectedCategory= rememberSaveable { mutableStateOf("") }
    val state = viewModel.state.collectAsState()
    val categoryToSearch = rememberSaveable {
        mutableStateOf(
            arrayListOf(
                "business",
                "entertainment",
                "general",
                "health",
                "science",
                "sports",
                "technology"
            )
        )
    }

    if (state.value.loading == true) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = modifier.fillMaxSize()
        ) {
            CircularProgressIndicator()
        }
    } else if (state.value.error.isNullOrBlank().not()) {
        Text(text = state.value.error.toString())

    } else {
        Column(modifier = modifier.fillMaxSize()) {
            Row(
                modifier = modifier
                    .fillMaxWidth(),
//                   horizontalArrangement = Arrangement.Center
            ) {
                OutlinedTextField(
                    shape = RoundedCornerShape(20.dp),
                    singleLine = true,
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp),
                    value = searchTerm.value,
                    onValueChange = {
                        searchTerm.value = it
                    },
                    placeholder = { Text(text = "Search") },
                    label = { Text(text = "Search") },
                    trailingIcon = {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = null,
                            modifier = modifier.clickable(
                                enabled = searchTerm.value.isNotBlank(),
                                onClick = {
                                    viewModel.getEverything(q = searchTerm.value)
                                })
                        )
                    })

            }
            Spacer(modifier = modifier.height(5.dp))

            LazyRow(modifier = modifier.fillMaxWidth(), state = scrollState) {
                items(categoryToSearch.value) {
                    Card(
                        colors = CardDefaults.cardColors(
                            containerColor = if (it === selectedCategory.value) {
//                            contentColor =  if (it.equals(selectedCategory.value)) {
                                MaterialTheme.colorScheme.primary
                            } else {
                                MaterialTheme.colorScheme.surfaceVariant
                            }
                        ),
                        modifier = modifier
                            .padding(horizontal = 5.dp)
                            .clickable {
                                viewModel.getEverything(q = it)
                                selectedCategory.value=it

                            }) {
                        Text(
                            it,
                            fontSize = 25.sp, modifier = modifier.padding(5.dp)
                        )
                    }
                }

            }
            Spacer(modifier = modifier.height(5.dp))
            val data = state.value.data
            if (data?.articles!!.isEmpty()) {
                Text(text = "No Data Found")
            } else {
                val article = data.articles.filter { article ->
                    article.title?.contains("Removed") == false
                }


                val articles = data.articles
                LazyColumn(modifier = modifier.fillMaxSize()) {
                    items(articles) { article ->
                        Card(
                            modifier = modifier
                                .fillMaxWidth()
                                .wrapContentHeight()
                                .wrapContentWidth(align = Alignment.CenterHorizontally)
                                .padding(horizontal = 15.dp,vertical = 8.dp)
                                .shadow(10.dp)
                                .clickable {
                                    navController.navigate(
                                        CategoryScreen(
                                            author = article.author,
                                            content = article.content,
                                            description = article.description,
                                            publishedAt = article.publishedAt,
                                            id = article.source!!.id,
                                            name = article.source.name,
                                            title = article.title,
                                            url = article.url,
                                            urlToImage = article.urlToImage
                                        )
                                    )
                                }) {
                            Column {

                                AsyncImage(
                                    modifier =modifier.padding(horizontal = 15.dp, vertical = 10.dp ).height(220.dp),
                                    model = article.urlToImage.toString(),
                                    contentDescription = null,
                                    placeholder = painterResource(R.drawable.ic_launcher_foreground),
                                )
                                article.title?.let { Text(text = it) }
                            }
                        }
                    }
                }
            }
        }
    }
}




