@file: OptIn(ExperimentalSharedTransitionApi::class)

package com.mahgoub.caffeine.ui.screen

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mahgoub.caffeine.R
import com.mahgoub.caffeine.ui.screen.onboarding.dropShadow
import com.mahgoub.caffeine.ui.theme.Black200
import com.mahgoub.caffeine.ui.theme.Sniglet
import com.mahgoub.caffeine.ui.theme.Urbanist
import com.mahgoub.caffeine.ui.theme.White100

@Composable
fun SharedTransitionScope.LastPageScreen(
    snackId: Int,
    navigateToOnBoarding: () -> Unit,
    animatedVisibilityScope: AnimatedVisibilityScope
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(White)
            .verticalScroll(rememberScrollState())
            .safeContentPadding()
            .padding(top = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            modifier = Modifier
                .align(Alignment.Start)
                .padding(start = 16.dp)
                .size(48.dp)
                .clip(RoundedCornerShape(50))
                .background(White100)
                .padding(12.dp),
            painter = painterResource(R.drawable.ic_cancel),
            contentDescription = null,
            tint = Black200
        )

        Row(
            modifier = Modifier.padding(top = 24.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            Icon(
                modifier = Modifier.size(32.dp),
                painter = painterResource(R.drawable.ic_coffee_beans),
                contentDescription = null,
                tint = Color(0xFF7C351B)
            )
            Text(
                modifier = Modifier.padding(top = 24.dp),
                text = "More Espresso, Less Depresso",
                color = Color(0xFF7C351B),
                fontFamily = Sniglet,
                fontWeight = FontWeight.Normal,
                fontSize = 20.sp,
                letterSpacing = 0.25.sp
            )
            Icon(
                modifier = Modifier.size(32.dp),
                painter = painterResource(R.drawable.ic_coffee_beans),
                contentDescription = null,
                tint = Color(0xFF7C351B)
            )
        }

        Image(
            modifier = Modifier
                .sharedElement(
                    sharedContentState = rememberSharedContentState(key = "snack/${snackId}"),
                    animatedVisibilityScope = animatedVisibilityScope
                )
                .padding(top = 64.dp)
                .width(300.dp),
            painter = painterResource(snackId),
            contentDescription = null,
            contentScale = ContentScale.FillWidth
        )

        Row(
            modifier = Modifier.padding(vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = "Bon appétit",
                color = Color(0xCC1F1F1F),
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = Urbanist,
                letterSpacing = 0.25.sp
            )
            Icon(
                modifier = Modifier.size(24.dp),
                painter = painterResource(R.drawable.ic_majic),
                contentDescription = null,
                tint = Color(0xCC1F1F1F)
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
                    blur = 8.dp,
                    spread = 0.dp
                )
                .height(56.dp),
            onClick = navigateToOnBoarding,
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF1F1F1F)
            ),
            contentPadding = PaddingValues(horizontal = 32.dp)
        ) {
            Text(
                text = "Thank youuu",
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