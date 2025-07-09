package com.mahgoub.caffeine.ui.screen.home

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mahgoub.caffeine.R
import com.mahgoub.caffeine.ui.screen.onboarding.dropShadow
import com.mahgoub.caffeine.ui.theme.Black200
import com.mahgoub.caffeine.ui.theme.Urbanist
import com.mahgoub.caffeine.ui.theme.White100
import kotlin.math.abs

@SuppressLint("ConfigurationScreenWidthHeight")
@Composable
fun HomeScreen(
    navigateToItemDetails: (Int) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(White)
            .statusBarsPadding()
            .verticalScroll(rememberScrollState())
            .padding(top = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
            ,
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Image(
                modifier = Modifier.size(48.dp),
                painter = painterResource(R.drawable.ghost_with_coffee),
                contentDescription = null,
            )

            Icon(
                modifier = Modifier
                    .size(48.dp)
                    .clip(RoundedCornerShape(50))
                    .background(White100)
                    .padding(12.dp),
                painter = painterResource(R.drawable.ic_add),
                contentDescription = null,
                tint = Black200
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            Text(
                modifier = Modifier.padding(top = 16.dp),
                text = "Good Morning",
                color = Color(0xFFB3B3B3),
                fontFamily = Urbanist,
                fontWeight = FontWeight.Bold,
                fontSize = 36.sp,
                letterSpacing = 0.25.sp
            )

            Text(
                text = "Ahmed ☀",
                color = Color(0xFF3B3B3B),
                fontFamily = Urbanist,
                fontWeight = FontWeight.Bold,
                fontSize = 36.sp,
                letterSpacing = 0.25.sp
            )

            Text(
                text = "What would you like to drink today?",
                color = Color(0xCC1F1F1F),
                fontFamily = Urbanist,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                letterSpacing = 0.25.sp
            )
        }

        val coffeeList = listOf(
            Pair<Int, String>(R.drawable.espresso, "Espresso"),
            Pair<Int, String>(R.drawable.macchiato, "Macchiato"),
            Pair<Int, String>(R.drawable.latte, "Latte"),
            Pair<Int, String>(R.drawable.black, "Black"),
        )
        val pagerState = rememberPagerState(
            initialPage = coffeeList.size - 1,
            pageCount = { coffeeList.size }
        )

        val width = LocalConfiguration.current.screenWidthDp

        val horizontalPadding = (width - 192) / 2
        HorizontalPager(
            state = pagerState,
            contentPadding = PaddingValues(horizontal = horizontalPadding.dp),
            pageSize = PageSize.Fixed(192.dp),
            modifier = Modifier
                .padding(top = 54.dp)
                .height(350.dp)
        ) { page ->
            val currentPageOffset =
                (pagerState.currentPage - page) + pagerState.currentPageOffsetFraction
            val scale = 1f - (0.4f * abs(currentPageOffset))
            val verticalOffset = 50.dp * abs(currentPageOffset)
            Box(
                modifier = Modifier.size(width = 198.dp, height = 298.dp),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Bottom,
                ) {
                    Image(
                        modifier = Modifier
                            .width(192.dp)
                            .height(244.dp)
                            .graphicsLayer {
                                scaleX = scale
                                scaleY = scale
                                translationY = verticalOffset.toPx()
                            },
                        painter = painterResource(coffeeList[page].first),
                        contentDescription = null,
                    )
                    Text(
                        modifier = Modifier.padding(top = 16.dp),
                        text = coffeeList[page].second,
                        color = Color(0xFF1F1F1F),
                        fontFamily = Urbanist,
                        fontWeight = FontWeight.Bold,
                        fontSize = 32.sp,
                        letterSpacing = 0.25.sp
                    )
                }
            }
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
                    blur = 8.dp,
                    spread = 0.dp
                )
                .height(56.dp),
            onClick = { navigateToItemDetails(pagerState.currentPage) },
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
}
