package com.teamjeffry.receptbokenbackend.recipe

import com.teamjeffry.receptbokenbackend.category.Category
import org.springframework.data.mongodb.repository.MongoRepository

interface RecipeRepository : MongoRepository<Recipe, String> {
    fun save(recipe: Recipe): Recipe
    fun findRecipeByName(name: String): Recipe
    fun findRecipesByCategories(categories: List<Category>): List<Recipe>
    fun findRecipesByTime(time: Int): List<Recipe>
    fun findRecipesByNameAndCategoriesAndTime(name: String, categories: List<Category>, time: Int): List<Recipe>
}