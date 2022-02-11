package com.teamjeffry.receptbokenbackend.recipe.dto

data class SaveRecipeRequest(
    val name: String,
    val description: String,
    val instruction: String,
    val time: Int,
    val ingredientNames: List<String>,
    val categoryNames: List<String>
)