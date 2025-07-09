package com.mahgoub.caffeine.ui.screen.preparingOrder

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.core.InfiniteRepeatableSpec
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mahgoub.caffeine.R
import com.mahgoub.caffeine.ui.theme.Sniglet
import com.mahgoub.caffeine.ui.theme.Urbanist
import kotlinx.coroutines.delay
import kotlin.math.sin

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.PreparingOrderScreen(
    animatedVisibilityScope: AnimatedVisibilityScope,
    size: String,
    selectedSize: String,
    navigateToTakeAway: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(White)
            .verticalScroll(rememberScrollState())
            .padding(top = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .statusBarsPadding()
                .padding(top = 107.dp)
                .fillMaxWidth()
                .height(340.dp)
                .padding(horizontal = 16.dp)
                .sharedElement(
                    rememberSharedContentState(
                        key = "Coffee With Size"
                    ),
                    animatedVisibilityScope = animatedVisibilityScope
                ),
            contentAlignment = Alignment.Center
        ) {
            val height by remember {
                mutableStateOf(
                    when (selectedSize) {
                        "L" -> 300.dp
                        "M" -> 244.dp
                        else -> 188.dp
                    }
                )
            }
            val logoSize by remember {
                mutableStateOf(
                    when (selectedSize) {
                        "L" -> 66.dp
                        "M" -> 54.dp
                        else -> 40.dp
                    }
                )
            }

            Text(
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(top = 60.dp),
                text = size,
                color = Color(0x99000000),
                fontFamily = Urbanist,
                fontWeight = FontWeight.Medium,
                fontSize = 14.sp,
                letterSpacing = 0.25.sp
            )

            Image(
                modifier = Modifier.height(height),
                painter = painterResource(R.drawable.empty_cup),
                contentDescription = null,
                contentScale = ContentScale.FillHeight
            )
            Image(
                modifier = Modifier.size(logoSize),
                painter = painterResource(R.drawable.coffee_the_chance),
                contentDescription = null,
                contentScale = ContentScale.FillHeight
            )
        }
        Spacer(Modifier.weight(1f))

        var preparingFinished by remember { mutableStateOf(false) }

        LaunchedEffect(Unit) {
            delay(4200)
            preparingFinished = true
            navigateToTakeAway()
        }

        if (!preparingFinished) {
            SnakeLoadingAnimation()
        }

        Text(
            text = "Almost Done",
            color = Color(0xDE1F1F1F),
            fontFamily = Urbanist,
            fontWeight = FontWeight.Bold,
            fontSize = 22.sp,
            letterSpacing = 0.25.sp
        )

        Text(
            modifier = Modifier.padding(top = 8.dp),
            text = "Your coffee will be finish in",
            color = Color(0x991F1F1F),
            fontFamily = Urbanist,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            letterSpacing = 0.25.sp
        )

        Row(
            modifier = Modifier
                .padding(top = 12.dp, bottom = 32.dp)
                .navigationBarsPadding(),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "CO",
                color = Color(0xFF7C351B),
                fontFamily = Sniglet,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 32.sp,
                letterSpacing = 0.25.sp
            )
            Colon()
            Text(
                text = "FF",
                color = Color(0xFF7C351B),
                fontFamily = Sniglet,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 32.sp,
                letterSpacing = 0.25.sp
            )
            Colon()
            Text(
                text = "EE",
                color = Color(0xFF7C351B),
                fontFamily = Sniglet,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 32.sp,
                letterSpacing = 0.25.sp
            )
        }
    }
}

@Composable
fun Colon() {
    Column(
        verticalArrangement = Arrangement.spacedBy(4.dp),
    ) {
        (1..2).forEach {
            Box(
                modifier = Modifier
                    .size(4.dp)
                    .clip(RoundedCornerShape(100f))
                    .background(Color(0x1F1F1F1F))
            )
        }
    }
}

@Composable
fun SnakeLoadingAnimation() {
    val progress = rememberInfiniteTransition().animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = InfiniteRepeatableSpec(
            repeatMode = RepeatMode.Reverse,
            animation = tween(2500),
        )
    )

    Canvas(
        modifier = Modifier
            .padding(bottom = 32.dp)
            .fillMaxWidth()
            .height(32.dp)
    ) {
        val path = Path()
        val width = size.width
        val centerY = size.height / 2f
        val endX = (width * progress.value).toInt()

        for (x in 0..endX step 2) {
            val y = centerY + sin(x * 0.03f) * 30f
            if (x == 0) path.moveTo(0f, y) else path.lineTo(x.toFloat(), y)
        }

        drawPath(
            path = path,
            color = Color(0xCC1F1F1F),
            style = Stroke(5f)
        )
    }
}
