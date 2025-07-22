package com.mahgoub.caffeine.ui.screen

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.util.lerp
import com.mahgoub.caffeine.R
import com.mahgoub.caffeine.ui.theme.Black200
import com.mahgoub.caffeine.ui.theme.Urbanist
import com.mahgoub.caffeine.ui.theme.White100
import kotlin.math.absoluteValue


@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.SnackChoiceScreen(
    navigateToCheckout: (Int) -> Unit,
    animatedVisibilityScope: AnimatedVisibilityScope,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(White)
            .verticalScroll(rememberScrollState())
            .safeContentPadding()
            .padding(top = 16.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Icon(
            modifier = Modifier
                .padding(start = 16.dp)
                .size(48.dp)
                .clip(RoundedCornerShape(50))
                .background(White100)
                .padding(12.dp),
            painter = painterResource(R.drawable.ic_cancel),
            contentDescription = null,
            tint = Black200
        )

        Text(
            modifier = Modifier.padding(start = 16.dp, top = 24.dp),
            text = "Take your snack",
            color = Black200,
            fontFamily = Urbanist,
            fontWeight = FontWeight.Bold,
            fontSize = 22.sp,
            letterSpacing = 0.25.sp
        )

        val snacks by remember {
            mutableStateOf(
                listOf(
                    R.drawable.first,
                    R.drawable.second,
                    R.drawable.third,
                    R.drawable.fourth,
                    R.drawable.fifth,
                    R.drawable.sixth
                )
            )
        }

        val verticalState = rememberPagerState { snacks.size }

        VerticalPager(
            state = verticalState,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 24.dp)
                .weight(1f),
            contentPadding = PaddingValues(top = 136.dp),
            beyondViewportPageCount = 6,
            pageSpacing = (-350).dp
        ) { snackId ->
            val pageOffsetFraction =
                (verticalState.currentPage - snackId) + verticalState.currentPageOffsetFraction

            val rotate = lerp(
                start = if (pageOffsetFraction == 0f) 0f else -8f,
                stop = 0f,
                fraction = 1f - pageOffsetFraction.coerceIn(-1f, 1f)
            )
            val offsetX = lerp(
                start = if (pageOffsetFraction == 0f) -48f else ((pageOffsetFraction.absoluteValue) * -180f),
                stop = -74f,
                fraction = 1f - pageOffsetFraction.absoluteValue.coerceIn(-1f, 1f)
            )
            val offsetY = lerp(
                start = ((pageOffsetFraction.absoluteValue) * 64f),
                stop = -0f,
                fraction = 1f - pageOffsetFraction.absoluteValue.coerceIn(-1f, 1f)
            )
            val scale = lerp(
                start = 0.7f,
                stop = 1f,
                fraction = 1f - pageOffsetFraction.coerceIn(-1f, 1f)
            )

            SnackItem(
                modifier = Modifier
                    .scale(scale)
                    .rotate(rotate)
                    .offset(x = offsetX.dp, y = offsetY.dp),
                snackImageId = snacks[snackId],
                onClick = { navigateToCheckout(it) },
                animatedVisibilityScope = animatedVisibilityScope
            )
        }
    }
}

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.SnackItem(
    snackImageId: Int,
    onClick: (Int) -> Unit,
    animatedVisibilityScope: AnimatedVisibilityScope,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .size(height = 274.dp, width = 260.dp)
            .clip(RoundedCornerShape(topEnd = 32.dp, bottomEnd = 32.dp))
            .clickable { onClick(snackImageId) }
            .background(Color(0xFFF5F5F5)),
        contentAlignment = Alignment.Center
    ) {
        Image(
            modifier = Modifier
                .sharedElement(
                    sharedContentState = rememberSharedContentState(key = "snack/${snackImageId}"),
                    animatedVisibilityScope = animatedVisibilityScope
                )
                .height(149.dp),
            painter = painterResource(snackImageId),
            contentDescription = null
        )
    }
}
