package com.teamjeffry.receptbokenbackend.recipe.dto

data class FindRecipeRequest(
    val recipeName: String,
    val categoryName: String,
    val time: Int
)
