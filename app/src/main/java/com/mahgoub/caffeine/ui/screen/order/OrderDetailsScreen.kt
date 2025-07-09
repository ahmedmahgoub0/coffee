package com.mahgoub.caffeine.ui.screen.order

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mahgoub.caffeine.R
import com.mahgoub.caffeine.ui.screen.onboarding.dropShadow
import com.mahgoub.caffeine.ui.theme.Black200
import com.mahgoub.caffeine.ui.theme.Urbanist
import com.mahgoub.caffeine.ui.theme.White100
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.OrderDetailsScreen(
    animatedVisibilityScope: AnimatedVisibilityScope,
    coffeeIndex: Int,
    navigateBack: () -> Unit,
    navigateToPreparingScreen: (String, String) -> Unit
) {
    var selectedSize by rememberSaveable { mutableStateOf("M") }
    var selectedQuantity by rememberSaveable { mutableStateOf("Low") }
    var previousQuantity by rememberSaveable { mutableStateOf("") }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(White)
                .verticalScroll(rememberScrollState())
                .padding(top = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            val coffeeList = listOf(
                Pair<Int, String>(R.drawable.espresso, "Espresso"),
                Pair<Int, String>(R.drawable.macchiato, "Macchiato"),
                Pair<Int, String>(R.drawable.latte, "Latte"),
                Pair<Int, String>(R.drawable.black, "Black"),
            )
            val coffee = coffeeList[coffeeIndex]

            var navigated by remember { mutableStateOf(false) }
            AnimatedVisibility(
                visible = !navigated,
                enter = fadeIn(),
                exit = slideOutVertically(targetOffsetY = { -it })
            ) {
                Row(
                    modifier = Modifier
                        .statusBarsPadding()
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        modifier = Modifier
                            .size(48.dp)
                            .clip(RoundedCornerShape(50))
                            .clickable { navigateBack() }
                            .background(White100)
                            .padding(12.dp),
                        painter = painterResource(R.drawable.ic_arrow_left),
                        contentDescription = null,
                        tint = Black200
                    )
                    Text(
                        modifier = Modifier.padding(start = 12.dp),
                        text = coffee.second,
                        color = Color(0xDE1F1F1F),
                        fontFamily = Urbanist,
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp,
                        letterSpacing = 0.25.sp
                    )
                }
            }
            Spacer(Modifier.height(22.dp))

            var sizeNumber by remember {
                mutableStateOf(
                    when (selectedSize) {
                        "S" -> "150 ML"
                        "M" -> "200 ML"
                        else -> "400 ML"
                    }
                )
            }

            Box(
                modifier = Modifier
                    .padding(top = 37.dp)
                    .fillMaxWidth()
                    .height(340.dp)
                    .padding(horizontal = 16.dp)
                    .sharedElement(
                        sharedContentState = rememberSharedContentState(
                            key = "Coffee With Size"
                        ),
                        animatedVisibilityScope = animatedVisibilityScope
                    ),
                contentAlignment = Alignment.Center
            ) {
                val transition = updateTransition(selectedSize)
                val height by transition.animateDp(
                    transitionSpec = { tween(800) }
                ) {
                    when (it) {
                        "L" -> 300.dp
                        "M" -> 244.dp
                        else -> 188.dp
                    }
                }
                val logoSize by transition.animateDp(
                    transitionSpec = { tween(800) }
                ) {
                    when (it) {
                        "L" -> 66.dp
                        "M" -> 54.dp
                        else -> 40.dp
                    }
                }
                AnimatedContent(
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .padding(top = 60.dp),
                    targetState = sizeNumber,
                    transitionSpec = { fadeIn(tween(800)) togetherWith (fadeOut(tween(800))) },
                ) { it ->
                    Text(
                        text = it,
                        color = Color(0x99000000),
                        fontFamily = Urbanist,
                        fontWeight = FontWeight.Medium,
                        fontSize = 14.sp,
                        letterSpacing = 0.25.sp

                    )
                }

                Image(
                    modifier = Modifier
                        .height(height),
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

            Row(
                modifier = Modifier
                    .height(56.dp)
                    .clip(RoundedCornerShape(100))
                    .background(Color(0xFFF5F5F5))
                    .padding(horizontal = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                SizeTextBox(
                    isSelected = selectedSize == "S",
                    size = "S",
                    onClick = {
                        selectedSize = "S"
                        sizeNumber = "150 ML"
                    }
                )
                SizeTextBox(
                    isSelected = selectedSize == "M",
                    size = "M",
                    onClick = {
                        selectedSize = "M"
                        sizeNumber = "200 ML"
                    }
                )
                SizeTextBox(
                    isSelected = selectedSize == "L",
                    size = "L",
                    onClick = {
                        selectedSize = "L"
                        sizeNumber = "400 ML"
                    }
                )
            }

            Row(
                modifier = Modifier
                    .padding(top = 16.dp)
                    .height(56.dp)
                    .clip(RoundedCornerShape(100))
                    .background(Color(0xFFF5F5F5))
                    .padding(horizontal = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                IconBox(
                    isSelected = selectedQuantity == "Low",
                    onClick = {
                        previousQuantity = selectedQuantity
                        selectedQuantity = "Low"
                    }
                )
                IconBox(
                    isSelected = selectedQuantity == "Medium",
                    onClick = {
                        previousQuantity = selectedQuantity
                        selectedQuantity = "Medium"
                    }
                )
                IconBox(
                    isSelected = selectedQuantity == "High",
                    onClick = {
                        previousQuantity = selectedQuantity
                        selectedQuantity = "High"
                    }
                )
            }
            Row(
                modifier = Modifier
                    .width(152.dp)
                    .padding(top = 2.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Low",
                    color = Color(0x991F1F1F),
                    fontFamily = Urbanist,
                    fontWeight = FontWeight.Medium,
                    fontSize = 10.sp,
                    letterSpacing = 0.25.sp
                )
                Text(
                    text = "Medium",
                    color = Color(0x991F1F1F),
                    fontFamily = Urbanist,
                    fontWeight = FontWeight.Medium,
                    fontSize = 10.sp,
                    letterSpacing = 0.25.sp
                )
                Text(
                    text = "High",
                    color = Color(0x991F1F1F),
                    fontFamily = Urbanist,
                    fontWeight = FontWeight.Medium,
                    fontSize = 10.sp,
                    letterSpacing = 0.25.sp
                )
            }
            Spacer(Modifier.weight(1f))
            Button(
                shape = RoundedCornerShape(50),
                modifier = Modifier
                    .navigationBarsPadding()
                    .padding(bottom = 24.dp)
                    .dropShadow(
                        shape = RoundedCornerShape(50),
                        color = Color(0xFF000000),
                        offsetY = 6.dp,
                        alpha = 0.24f,
                        blur = 12.dp,
                        spread = 0.dp
                    )
                    .height(56.dp),
                onClick = {
                    navigated = true
                    navigateToPreparingScreen(sizeNumber, selectedSize)
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF1F1F1F)
                ),
                contentPadding = PaddingValues(horizontal = 32.dp)
            ) {
                Text(
                    text = "Continue",
                    fontFamily = Urbanist,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    letterSpacing = 0.25.sp,
                    color = White.copy(alpha = 0.87f)
                )
                Icon(
                    modifier = Modifier.padding(start = 8.dp),
                    painter = painterResource(R.drawable.ic_arrow_right),
                    contentDescription = null,
                    tint = White.copy(alpha = 0.87f)
                )
            }
        }

        val offsetY = remember { Animatable(-300f) }
        val alpha = remember { Animatable(1f) }
        LaunchedEffect(selectedQuantity) {
            val isGoingDown = when {
                previousQuantity == "Low" && selectedQuantity == "Medium" -> true
                previousQuantity == "Low" && selectedQuantity == "High" -> true
                previousQuantity == "Medium" && selectedQuantity == "High" -> true
                else -> false
            }
            alpha.snapTo(1f)
            offsetY.snapTo(
                when (isGoingDown) {
                    true -> {
                        if (selectedSize == "S") -300f
                        else if (selectedSize == "M") -300f
                        else -300f
                    }

                    false -> {
                        if (selectedSize == "S") 227f
                        else if (selectedSize == "M") 167f
                        else 107f
                    }
                }
            )
            coroutineScope {
                launch {
                    alpha.animateTo(
                        targetValue = 0f,
                        animationSpec = tween(durationMillis = 700)
                    )
                }
                launch {
                    offsetY.animateTo(
                        targetValue =
                            when (selectedSize) {
                                "L" -> if (isGoingDown) 100f else -300f
                                "M" -> if (isGoingDown) 150f else -300f
                                else -> if (isGoingDown) 210f else -300f
                            },
                        animationSpec = tween(durationMillis = 500)
                    )
                }
            }
        }

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.TopCenter
        ) {
            Image(
                painter = painterResource(id = R.drawable.beans),
                contentDescription = null,
                modifier = Modifier
                    .graphicsLayer {
                        translationY = offsetY.value
                        this.alpha = alpha.value
                    }
                    .size(200.dp)
                    .scale(0.6f)
            )
        }
    }
}

@Composable
private fun SizeTextBox(
    isSelected: Boolean,
    size: String,
    onClick: () -> Unit
) {
    val textBackgroundColor by animateColorAsState(
        targetValue = when (isSelected) {
            true -> Color(0xDEFFFFFF)
            false -> Color(0x991F1F1F)
        }
    )
    val backgroundColor by animateColorAsState(
        targetValue = when (isSelected) {
            true -> Color(0xFF7C351B)
            false -> Transparent
        }
    )

    val selectedSizeModifier = Modifier
        .dropShadow(
            shape = RoundedCornerShape(100),
            color = Color(0xFFB94B23),
            offsetY = 8.dp,
            blur = 16.dp,
            alpha = 0.5f
        )
        .size(40.dp)
        .clip(RoundedCornerShape(100))
        .background(backgroundColor)

    Box(
        modifier = Modifier
            .size(40.dp)
            .then(if (isSelected) selectedSizeModifier else Modifier)
            .clip(RoundedCornerShape(100))
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Text(
            textAlign = TextAlign.Center,
            modifier = Modifier,
            text = size,
            color = textBackgroundColor,
            fontFamily = Urbanist,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            letterSpacing = 0.25.sp
        )
    }
}

@Composable
private fun IconBox(
    isSelected: Boolean,
    painter: Painter = painterResource(R.drawable.ic_coffee_beans),
    onClick: () -> Unit
) {
    val iconTint by animateColorAsState(
        targetValue = when (isSelected) {
            true -> Color(0xDEFFFFFF)
            false -> Transparent
        }
    )
    val backgroundColor by animateColorAsState(
        targetValue = when (isSelected) {
            true -> Color(0xFF7C351B)
            false -> Transparent
        },
    )

    val selectedSizeModifier = Modifier
        .dropShadow(
            shape = RoundedCornerShape(100),
            color = Color(0xFFB94B23),
            offsetY = 8.dp,
            blur = 16.dp,
            alpha = 0.5f
        )
        .size(40.dp)
        .clip(RoundedCornerShape(100))
        .background(backgroundColor)

    Box(
        modifier = Modifier
            .size(40.dp)
            .then(if (isSelected) selectedSizeModifier else Modifier)
            .clip(RoundedCornerShape(100))
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Icon(
            modifier = Modifier,
            painter = painter,
            contentDescription = null,
            tint = iconTint
        )
    }
}