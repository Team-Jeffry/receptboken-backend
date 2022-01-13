package com.teamjeffry.receptbokenbackend.recipe

import com.teamjeffry.receptbokenbackend.category.CategoryRepository
import com.teamjeffry.receptbokenbackend.category.Category
import com.teamjeffry.receptbokenbackend.ingredient.Ingredient
import com.teamjeffry.receptbokenbackend.ingredient.IngredientRepository
import com.teamjeffry.receptbokenbackend.recipe.dto.FindRecipeRequest
import com.teamjeffry.receptbokenbackend.recipe.dto.SaveRecipeRequest
import org.bson.types.ObjectId
import org.springframework.stereotype.Service

@Service
class RecipeService(
    private val recipeRepository: RecipeRepository,
    private val categoryRepository: CategoryRepository,
    private val ingredientRepository: IngredientRepository
) {

    fun save(request: SaveRecipeRequest): Recipe {

        request.ingredients
            .filter { !ingredientRepository.existsByName(it.name) }
            .forEach { ingredientRepository.save(it.copy(id = ObjectId.get())) }

        val ingredientsFromDb: List<Ingredient> =
            request.ingredients.map { ingredientRepository.findIngredientByName(it.name) }

        val categoriesFromDb: List<Category> = request.categoryNames
            .filter { categoryRepository.existsByName(it) }
            .map { categoryRepository.findCategoryByName(it) }

        val recipe: Recipe = Recipe(
            id = ObjectId.get(),
            name = request.name,
            description = request.description,
            instruction = request.instruction,
            time = request.time,
            ingredients = ingredientsFromDb,
            categories = categoriesFromDb
        )

        println("Saved!")
        return recipeRepository.save(recipe)
    }

    fun findRecipe(request: FindRecipeRequest): List<Recipe> {
        if (request.categoryName.isBlank() && request.time == 0 && request.recipeName.isNotBlank()) {
            return listOf(recipeRepository.findRecipeByName(request.recipeName))
        }
        if (request.recipeName.isBlank() && request.categoryName.isBlank() && request.time != 0) {
            return recipeRepository.findRecipesByTime(request.time)
        }
        if (request.recipeName.isBlank() && request.time == 0 && request.categoryName.isNotBlank()) {
            return recipeRepository.findRecipesByCategories(listOf(categoryRepository.findCategoryByName(request.categoryName)))
        }
        return recipeRepository.findRecipesByNameAndCategoriesAndTime(
            request.recipeName,
            listOf(categoryRepository.findCategoryByName(request.categoryName)),
            request.time
        )
    }


}