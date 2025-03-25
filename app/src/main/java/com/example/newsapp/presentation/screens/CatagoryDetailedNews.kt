package com.example.newsapp.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil3.compose.AsyncImage
import com.example.newsapp.data.model.Article
import com.example.newsapp.data.model.Source

@Preview(showSystemUi = true, showBackground = true)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryDetailScreenUI(
    modifier: Modifier = Modifier,
    navController: NavController = rememberNavController(),
    article: Article = Article(
        source = Source(
            id = "test",
            name = "test",
        ),
        author = "Lauren Forristal",
        title = "Bye-bye bots: Altera's game-playing AI agents get backing from Eric Schmidt | TechCrunch",
        description = "Autonomous, AI-based players are coming to a gaming experience near you, and a new startup, Altera, is joining the fray to build this new guard of AI Research company Altera raised \$9 million to build AI agents that can play video games alongside other player…",
        url = "https://techcrunch.com/2024/05/08/bye-bye-bots-alteras-game-playing-ai-agents-get-backing-from-eric-schmidt/",
        urlToImage = "https://techcrunch.com/wp-content/uploads/2024/05/Minecraft-keyart.jpg?resize=1200,720",
        content = "Autonomous, AI-based players are coming to a gaming experience near you, and a new startup, Altera, is joining the fray to build this new guard of AI agents.\\r\\nThe company announced Wednesday that it … [+6416 chars]"

    )
) {

    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Detail Screen",
                        fontStyle = FontStyle.Italic,
                        style = TextStyle(
                            fontSize = MaterialTheme.typography.titleLarge.fontSize,
                            color = MaterialTheme.colorScheme.onPrimaryContainer,
                            fontWeight = FontWeight.Bold,
                            fontFamily = FontFamily.Serif,
                            letterSpacing = 2.sp,
                            lineHeight = 30.sp,

                            )


                    )

                }
            )
        }

    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(it),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = modifier.height(24.dp))
            Row(Modifier.padding(10.dp)) {
                Text(
                    text = article.title.toString(),
                    fontStyle = FontStyle.Italic,
                    style = TextStyle(
                        fontSize = MaterialTheme.typography.titleLarge.fontSize,
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.Serif,
                        letterSpacing = 2.sp,
                        lineHeight = 24.sp,

                        )
                )
            }

            Spacer(modifier = modifier.height(24.dp))
            Card(modifier = modifier
                .padding(20.dp)
                .shadow(10.dp)) {
                AsyncImage(
                    model = article.urlToImage,
                    contentDescription = null,


                    )
            }
            Row(Modifier.padding(10.dp)) {
                Text(
                    text = article.description.toString(),
                    fontStyle = FontStyle.Italic,
                    style = TextStyle(
                        fontSize = MaterialTheme.typography.titleLarge.fontSize,
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.Serif,
                        letterSpacing = 2.sp,
                        lineHeight = 24.sp,

                        )
                )


            }


        }
    }
}