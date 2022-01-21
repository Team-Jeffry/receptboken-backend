package com.teamjeffry.receptbokenbackend.recipe

import com.teamjeffry.receptbokenbackend.category.Category
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface RecipeRepository : MongoRepository<Recipe, String> {
    fun save(recipe: Recipe): Recipe
    fun findRecipesByNameContaining(name: String): List<Recipe>
    fun findRecipesByCategoriesContaining(categories: List<Category>): List<Recipe>
    fun findRecipesByTime(time: Int): List<Recipe>
    fun findRecipesByNameContainingAndCategoriesContaining(name: String, categories: List<Category>): List<Recipe>
    fun findRecipesByCategoriesContainingAndTime(categories: List<Category>, time: Int): List<Recipe>
    fun findRecipesByNameContainingAndTime(name: String, time: Int): List<Recipe>
    fun findRecipesByNameContainingAndCategoriesContainingAndTime(name: String, categories: List<Category>, time: Int): List<Recipe>
}