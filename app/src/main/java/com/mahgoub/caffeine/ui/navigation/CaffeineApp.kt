package com.mahgoub.caffeine.ui.navigation

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mahgoub.caffeine.ui.screen.LastPageScreen
import com.mahgoub.caffeine.ui.screen.SnackChoiceScreen
import com.mahgoub.caffeine.ui.screen.home.HomeScreen
import com.mahgoub.caffeine.ui.screen.onboarding.OnboardingScreen
import com.mahgoub.caffeine.ui.screen.order.OrderDetailsScreen
import com.mahgoub.caffeine.ui.screen.preparingOrder.PreparingOrderScreen
import com.mahgoub.caffeine.ui.screen.takeaway.TakeAwayScreen

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun CaffeineApp(modifier: Modifier = Modifier) {
    val navController = rememberNavController()

    SharedTransitionLayout(
        modifier = modifier.fillMaxSize()
    ) {
        NavHost(
            navController = navController,
            startDestination = OnboardingRoute
        ) {
            composable<OnboardingRoute> {
                OnboardingScreen { navController.navigate(HomeRoute) }
            }
            composable<HomeRoute> {
                HomeScreen { coffeeIndex ->
                    navController.navigate(
                        OrderDetailsRoute(
                            coffeeIndex
                        )
                    )
                }
            }
            composable<OrderDetailsRoute>(
                enterTransition = {
                    slideInVertically(
                        initialOffsetY = { fullHeight -> fullHeight },
                        animationSpec = tween(500)
                    )
                },
                popEnterTransition = {
                    slideInVertically(
                        initialOffsetY = { fullHeight -> -fullHeight },
                        animationSpec = tween(500)
                    )
                },
            ) { navStackEntry ->
                OrderDetailsScreen(
                    animatedVisibilityScope = this@composable,
                    coffeeIndex = navStackEntry.arguments?.getInt("coffeeIndex") ?: 0,
                    navigateBack = { navController.popBackStack() },
                    navigateToPreparingScreen = { size, selectedSize ->
                        navController.navigate(
                            PreparingRoute(
                                size,
                                selectedSize
                            )
                        )
                    }
                )
            }
            composable<PreparingRoute> { navStackEntry ->
                PreparingOrderScreen(
                    animatedVisibilityScope = this@composable,
                    size = navStackEntry.arguments?.getString("size") ?: "",
                    selectedSize = navStackEntry.arguments?.getString("selectedSize") ?: "",
                ) {
                    navController.navigate(TakeAwayRoute)
                }
            }
            composable<TakeAwayRoute> {
                TakeAwayScreen(
                    onClickCancel = { navController.popBackStack() },
                    navigateToOnboarding = { navController.navigate(SnackChoiceRoute) }
                )
            }
            composable<SnackChoiceRoute> {
                SnackChoiceScreen(
                    animatedVisibilityScope = this@composable,
                    navigateToCheckout = { navController.navigate(LastPageRoute(it)) }
                )

            }
            composable<LastPageRoute> {
                LastPageScreen(
                    snackId = it.arguments?.getInt("snackId") ?: 0,
                    navigateToOnBoarding = { navController.navigate(OnboardingRoute) },
                    animatedVisibilityScope = this@composable
                )
            }
        }
    }
}

// navController.popBackStack(OnboardingRoute, false)