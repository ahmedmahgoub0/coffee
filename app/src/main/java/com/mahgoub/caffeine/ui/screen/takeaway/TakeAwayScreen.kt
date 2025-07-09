package com.mahgoub.caffeine.ui.screen.takeaway

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
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
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
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

@Composable
fun TakeAwayScreen(
    onClickCancel: () -> Unit,
    navigateToOnboarding: () -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(White)
            .verticalScroll(rememberScrollState())
            .padding(top = 16.dp),
        contentAlignment = Alignment.TopCenter
    ) {
        var visible by remember { mutableStateOf(false) }
        LaunchedEffect(Unit) {
            visible = true
        }

        AnimatedVisibility(
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(start = 16.dp),
            visible = visible,
            enter = slideInVertically(initialOffsetY = { -it })
        ) {
            Icon(
                modifier = Modifier
                    .statusBarsPadding()
                    .size(48.dp)
                    .clip(RoundedCornerShape(50))
                    .clickable { onClickCancel() }
                    .background(White100)
                    .padding(12.dp),
                painter = painterResource(R.drawable.ic_cancel),
                contentDescription = null,
                tint = Black200
            )
        }

        Box(
            modifier = Modifier
                .statusBarsPadding()
                .padding(top = 260.dp)
                .height(300.dp),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(R.drawable.empty_cup),
                contentDescription = null,
                contentScale = ContentScale.FillHeight
            )
            Image(
                painter = painterResource(R.drawable.coffee_the_chance),
                contentDescription = null,
                modifier = Modifier.size(64.dp)
            )
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AnimatedVisibility(
                visible = visible,
                enter = slideInVertically(initialOffsetY = { -it })
            ) {
                Column(
                    modifier = Modifier
                        .padding(top = 120.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        modifier = Modifier
                            .dropShadow(
                                shape = RoundedCornerShape(100),
                                color = Color(0x80B94B23),
                                offsetY = 6.dp,
                                blur = 8.dp,
                                alpha = 0.4f
                            )
                            .size(56.dp)
                            .clip(RoundedCornerShape(50))
                            .background(Color(0xFF7C351B))
                            .padding(12.dp),
                        painter = painterResource(R.drawable.ic_tick),
                        contentDescription = null,
                        tint = Color(0xDEFFFFFF)
                    )

                    Text(
                        modifier = Modifier
                            .padding(top = 24.dp)
                            .width(207.dp),
                        text = "Your coffee is ready, Enjoy",
                        fontFamily = Urbanist,
                        fontSize = 22.sp,
                        color = Color(0xDE1F1F1F),
                        textAlign = TextAlign.Center,
                        letterSpacing = 0.25.sp,
                    )
                    Image(
                        painter = painterResource(R.drawable.cup_lid),
                        contentDescription = null,
                        modifier = Modifier
                            .padding(top = 17.dp)
                            .height(69.dp),
                        contentScale = ContentScale.FillHeight
                    )
                }
            }
        }

        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                var buttonOn by remember { mutableStateOf(false) }
                val buttonColor by animateColorAsState(
                    when (buttonOn) {
                        true -> Color(0xFF7C351B)
                        false -> Color(0xFFFFEEE7)
                    },
                    animationSpec = tween(700)
                )
                Box(
                    modifier = Modifier
                        .size(width = 78.dp, height = 40.dp)
                        .clip(RoundedCornerShape(100f))
                        .clickable { buttonOn = !buttonOn }
                        .background(buttonColor)
                        .padding(1.dp)
                ) {
                    Text(
                        modifier = Modifier
                            .align(Alignment.CenterStart)
                            .padding(start = 14.dp),
                        text = "OFF",
                        fontFamily = Urbanist,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 0.25.sp,
                        color = Color(0x99000000)
                    )
                    Text(
                        modifier = Modifier
                            .align(Alignment.CenterEnd)
                            .padding(end = 14.dp),
                        text = "ON",
                        fontFamily = Urbanist,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 0.25.sp,
                        color = Color(0x99FFFFFF)
                    )
                    val imageRotation by animateFloatAsState(
                        when (buttonOn) {
                            true -> 0f
                            false -> 270f
                        },
                        animationSpec = tween(700)
                    )
                    val imageAlign by animateDpAsState(
                        when (buttonOn) {
                            true -> 0.dp
                            false -> 37.dp
                        },
                        animationSpec = tween(700)
                    )
                    Image(
                        painter = painterResource(R.drawable.switch_button),
                        contentDescription = null,
                        modifier= Modifier
                            .offset(x = imageAlign)
                            .rotate(imageRotation)
                    )
                }

                Text(
                    text = "Take Away",
                    fontFamily = Urbanist,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 0.25.sp,
                    color = Color(0xB3000000)
                )
            }
            AnimatedVisibility(
                visible = visible,
                enter = slideInVertically(initialOffsetY = { it })
            ) {
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
                            blur = 8.dp,
                            spread = 0.dp
                        )
                        .height(56.dp),
                    onClick = navigateToOnboarding,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF1F1F1F)
                    ),
                    contentPadding = PaddingValues(horizontal = 32.dp)
                ) {
                    Text(
                        text = "Take snack",
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
        }
    }
}