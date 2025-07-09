package com.mahgoub.caffeine.ui.navigation

import kotlinx.serialization.Serializable

@Serializable
object OnboardingRoute

@Serializable
object HomeRoute

@Serializable
data class OrderDetailsRoute(
    val coffeeIndex: Int
)

@Serializable
data class PreparingRoute(
    val size: String,
    val selectedSize: String
)

@Serializable
object TakeAwayRoute

