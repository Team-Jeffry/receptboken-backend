package com.teamjeffry.receptbokenbackend.recipe.dto

import com.teamjeffry.receptbokenbackend.ingredient.Ingredient

data class SaveRecipeRequest(
    val name: String,
    val description: String,
    val instruction: String,
    val time: Int,
    val ingredients: List<Ingredient>,
    val categoryNames: List<String>
)