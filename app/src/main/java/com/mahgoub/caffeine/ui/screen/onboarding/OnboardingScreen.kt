package com.mahgoub.caffeine.ui.screen.onboarding

import android.graphics.BlurMaskFilter
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.InfiniteRepeatableSpec
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.BlurredEdgeTreatment
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.drawOutline
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mahgoub.caffeine.R
import com.mahgoub.caffeine.ui.theme.Black200
import com.mahgoub.caffeine.ui.theme.Sniglet
import com.mahgoub.caffeine.ui.theme.Urbanist
import com.mahgoub.caffeine.ui.theme.White100

@Composable
fun OnboardingScreen(
    navigateToHome: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(White)
            .verticalScroll(rememberScrollState())
            .padding(top = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .statusBarsPadding()
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
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

        Box(
            modifier = Modifier
                .padding(top = 16.dp)
                .width(188.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Hocus\nPocus\nI Need Coffee\nto Focus",
                fontFamily = Sniglet,
                fontWeight = FontWeight.Normal,
                fontSize = 32.sp,
                lineHeight = 50.sp,
                letterSpacing = 0.25.sp,
                textAlign = TextAlign.Center,
                color = Black200
            )

            val infiniteTransition = rememberInfiniteTransition()
            val starAlpha by infiniteTransition.animateColor(
                initialValue = Color(0xDE000000),
                targetValue = Color(0x1F000000),
                label = "ghost with shadow",
                animationSpec = InfiniteRepeatableSpec(
                    repeatMode = RepeatMode.Reverse,
                    animation = tween(800)
                )
            )

            Icon(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(top = 6.dp)
                    .offset(x = 1.dp),
                painter = painterResource(R.drawable.ic_star),
                contentDescription = null,
                tint = starAlpha
            )

            Icon(
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(top = 65.dp, start = 10.dp),
                painter = painterResource(R.drawable.ic_star),
                contentDescription = null,
                tint = starAlpha
            )

            Icon(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(top = 6.dp)
                    .offset(x = 15.dp, y = 2.dp),
                painter = painterResource(R.drawable.ic_star),
                contentDescription = null,
                tint = starAlpha
            )
        }

        val infiniteTransition = rememberInfiniteTransition()
        val imagePadding by infiniteTransition.animateFloat(
            initialValue = 16f,
            targetValue = 48f,
            animationSpec = InfiniteRepeatableSpec(
                repeatMode = RepeatMode.Reverse,
                animation = tween(2000)
            )
        )

        val shadowPadding by infiniteTransition.animateFloat(
            initialValue = 48f,
            targetValue = 0f,
            animationSpec = InfiniteRepeatableSpec(
                repeatMode = RepeatMode.Reverse,
                animation = tween(2000)
            )
        )

        Image(
            painterResource(R.drawable.ghost),
            contentDescription = null,
            modifier = Modifier
                .padding(top = imagePadding.dp)
                .size(244.dp)
        )

        val shadowColor by rememberInfiniteTransition().animateColor(
            initialValue = Color(0x14000000),
            targetValue = Color(0x24000000),
            animationSpec = InfiniteRepeatableSpec(
                repeatMode = RepeatMode.Reverse,
                animation = tween(2000)
            )
        )

        Box(
            modifier = Modifier
                .padding(top = shadowPadding.dp)
                .size(width = 174.dp, height = 27.dp)
                .graphicsLayer {
                    this.rotationX = 45f
                }
                .blur(
                    radius = 12.dp,
                    edgeTreatment = BlurredEdgeTreatment.Unbounded
                )
                .drawWithContent {
                    drawOval(
                        color = shadowColor,
                        alpha = 0.5f,
                    )
                }
        )

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
            onClick = navigateToHome,
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF1F1F1F)
            ),
            contentPadding = PaddingValues(horizontal = 32.dp)
        ) {
            Text(
                text = "bring my coffee",
                fontFamily = Urbanist,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                letterSpacing = 0.25.sp,
                color = White.copy(alpha = 0.87f)
            )
            Icon(
                modifier = Modifier.padding(start = 8.dp),
                painter = painterResource(R.drawable.ic_cup_of_coffe),
                contentDescription = null,
                tint = White.copy(alpha = 0.87f)
            )
        }
    }
}

@Composable
@Preview
fun OnboardingScreenPreview() {
    OnboardingScreen {}
}

@Stable
fun Modifier.dropShadow(
    shape: Shape,
    color: Color,
    alpha: Float = 0.5f,
    blur: Dp = 4.dp,
    offsetY: Dp = 0.dp,
    offsetX: Dp = 0.dp,
    spread: Dp = 0.dp
) = this.drawBehind {
    this.drawIntoCanvas {
        val shadowSize = Size(size.width + spread.toPx(), size.height + spread.toPx())
        val shadowOutline = shape.createOutline(shadowSize, layoutDirection, this)

        val paint = Paint()
        paint.color = color.copy(alpha)

        if (blur.toPx() > 0) {
            paint.asFrameworkPaint().apply {
                maskFilter = BlurMaskFilter((blur * 2).toPx(), BlurMaskFilter.Blur.NORMAL)
            }
        }

        drawIntoCanvas { canvas ->
            canvas.save()
            canvas.translate(offsetX.toPx(), offsetY.toPx())
            canvas.drawOutline(shadowOutline, paint)
            canvas.restore()
        }
    }
}