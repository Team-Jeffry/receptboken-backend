package com.teamjeffry.receptbokenbackend.recipe.dto

import com.fasterxml.jackson.annotation.JsonInclude

@JsonInclude(JsonInclude.Include.NON_EMPTY)
data class GetRecipeRequest(
    val recipeName: String,
    val categoryNames: List<String>,
    val time: Int
)
