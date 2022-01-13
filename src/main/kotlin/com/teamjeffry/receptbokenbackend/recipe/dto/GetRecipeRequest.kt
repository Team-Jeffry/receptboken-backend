package com.teamjeffry.receptbokenbackend.recipe.dto

data class GetRecipeRequest(
    val recipeName: String,
    val categoryNames: List<String>,
    val time: Int
)
