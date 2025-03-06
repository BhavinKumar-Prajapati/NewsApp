package com.example.newsapp.presentation.navigation.Routes

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.newsapp.data.model.Article
import com.example.newsapp.data.model.Source
import com.example.newsapp.presentation.NewsAppViewModel
import com.example.newsapp.presentation.screens.CategoryDetailScreenUI
import com.example.newsapp.presentation.screens.HomeScreenUI


@Composable
fun AppNavigation(modifier: Modifier = Modifier, viewModel: NewsAppViewModel) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = HomeScreen) {
        composable<HomeScreen> {
            HomeScreenUI(navController = navController, viewModel = viewModel)
        }
        composable<CategoryScreen> {
            val categoryScreen = it.toRoute<CategoryScreen>()
            var article = Article(
                author = categoryScreen.author,
                content = categoryScreen.content,
                description = categoryScreen.description,
                publishedAt = categoryScreen.publishedAt,
                source = Source(
                    id = categoryScreen.id,
                    name = categoryScreen.name
                ),
                title = categoryScreen.title,
                url = categoryScreen.url,
                urlToImage = categoryScreen.urlToImage
            )
            CategoryDetailScreenUI(article = article)
        }
    }

}