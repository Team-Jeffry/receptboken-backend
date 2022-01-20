package com.teamjeffry.receptbokenbackend.recipe

import com.teamjeffry.receptbokenbackend.category.Category
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface RecipeRepository : MongoRepository<Recipe, String> {
    fun save(recipe: Recipe): Recipe
    fun findRecipeByName(name: String): Recipe
    fun findRecipesByCategoriesContaining(categories: List<Category>): List<Recipe>
    fun findRecipesByTime(time: Int): List<Recipe>
    fun findRecipeByNameAndCategoriesContaining(name: String, categories: List<Category>): Recipe
    fun findRecipesByCategoriesContainingAndTime(categories: List<Category>, time: Int): List<Recipe>
    fun findRecipeByNameAndTime(name: String, time: Int): Recipe
    fun findRecipeByNameAndCategoriesContainingAndTime(name: String, categories: List<Category>, time: Int): Recipe
}