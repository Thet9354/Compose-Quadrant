package com.example.composequadrant

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.composequadrant.ui.theme.ComposeQuadrantTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeQuadrantTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val navController = rememberNavController()
                    if (intent?.action == Intent.ACTION_VIEW) {
                        // The code below handles the deep link here
                        val deepLinkUri = intent.data
                        if (deepLinkUri != null) {
                            val deepLink = deepLinkUri.toString()
                            if (deepLink == "https://www.betrbeta.com/#start") {
                                navController.navigate("main") // Navigate to the desired screen
                            }
                        }
                    }

                    NavHost(navController, startDestination = "main") {
                        composable("main") { ComposeQuadrantApp() }
//                        composable("main") { Article() }

                    }
                }
            }
        }
    }
}

@Composable
fun ComposeQuadrantApp() {
    val fadeAnim = fadeInAnimation()

    Column(Modifier.fillMaxWidth()) {
        Row(Modifier.weight(1f)) {
            ComposableInfoCard(
                title = stringResource(R.string.first_title),
                description = stringResource(R.string.first_description),
                backgroundColor = Color(0xFFEADDFF),
                modifier = Modifier
                    .weight(1f)
                    .alpha(fadeAnim[0].value)
            )
            ComposableInfoCard(
                title = stringResource(R.string.second_title),
                description = stringResource(R.string.second_description),
                backgroundColor = Color(0xFFD0BCFF),
                modifier = Modifier
                    .weight(1f)
                    .alpha(fadeAnim[1].value)
            )
        }
        Row(Modifier.weight(1f)) {
            ComposableInfoCard(
                title = stringResource(R.string.third_title),
                description = stringResource(R.string.third_description),
                backgroundColor = Color(0xFFB69DF8),
                modifier = Modifier
                    .weight(1f)
                    .alpha(fadeAnim[2].value)
            )
            ComposableInfoCard(
                title = stringResource(R.string.fourth_title),
                description = stringResource(R.string.fourth_description),
                backgroundColor = Color(0xFFF6EDFF),
                modifier = Modifier
                    .weight(1f)
                    .alpha(fadeAnim[3].value)
            )
        }
    }
}

@Composable
fun fadeInAnimation(): List<Animatable<Float, AnimationVector1D>> {

    Log.d("ComposeQuadrant", "Before launching the animation")
    val animationSpec = tween<Float>(durationMillis = 500)

    return remember {
        List(4) {
            Animatable(0f)
        }
    }.also { alphaList ->
        LaunchedEffect(Unit) {
            alphaList.forEachIndexed { index, alphaAnim ->
                Log.d("ComposeQuadrant", "Animating card at index: $index")
                launch {
                    delay(index * 200L)
                    alphaAnim.animateTo(1f, animationSpec = animationSpec)
                }
            }
        }
    }

    Log.d("ComposeQuadrant", "After launching the animation")

}

@Composable
private fun ComposableInfoCard(
    title: String,
    description: String,
    backgroundColor: Color,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(backgroundColor)
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = title,
            modifier = Modifier.padding(bottom = 16.dp),
            fontWeight = FontWeight.Bold
        )
        Text(
            text = description,
            textAlign = TextAlign.Justify
        )
    }
}



@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeQuadrantTheme {
        ComposeQuadrantApp()
    }
}

