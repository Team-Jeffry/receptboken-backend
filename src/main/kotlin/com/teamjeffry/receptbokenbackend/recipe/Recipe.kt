package com.teamjeffry.receptbokenbackend.recipe

import com.fasterxml.jackson.annotation.JsonInclude
import com.teamjeffry.receptbokenbackend.category.Category
import com.teamjeffry.receptbokenbackend.ingredient.Ingredient
import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Document
data class Recipe(
    @Id
    val id: ObjectId = ObjectId.get(),
    val name: String,
    val description: String,
    val instruction: String,
    val time: Int,
    val ingredients: List<Ingredient>,
    val categories: List<Category>
)
