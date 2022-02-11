package com.teamjeffry.receptbokenbackend.recipe

import com.teamjeffry.receptbokenbackend.category.CategoryRepository
import com.teamjeffry.receptbokenbackend.category.Category
import com.teamjeffry.receptbokenbackend.ingredient.Ingredient
import com.teamjeffry.receptbokenbackend.ingredient.IngredientRepository
import com.teamjeffry.receptbokenbackend.recipe.dto.GetRecipeRequest
import com.teamjeffry.receptbokenbackend.recipe.dto.SaveRecipeRequest
import org.bson.types.ObjectId
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class RecipeService(
    private val recipeRepository: RecipeRepository,
    private val categoryRepository: CategoryRepository,
    private val ingredientRepository: IngredientRepository
) {

    @Transactional
    fun save(request: SaveRecipeRequest): Recipe {

        saveNewIngredientsIfNotExists(request.ingredientNames);

        val ingredientsFromDb: List<Ingredient> =
            request.ingredientNames.map { ingredientRepository.findIngredientByName(it) }

        val categoriesFromDb: MutableList<Category> = request.categoryNames
            .filter { categoryRepository.existsByName(it) }
            .map { categoryRepository.findCategoryByName(it) }
            .toMutableList()

        if (categoriesFromDb.isEmpty()) {
            categoriesFromDb.add(categoryRepository.findCategoryByName("Ingen kategori"))
        }

        val recipe: Recipe = Recipe(
            id = ObjectId.get(),
            name = request.name.lowercase(),
            description = request.description,
            instruction = request.instruction,
            time = request.time,
            ingredients = ingredientsFromDb,
            categories = categoriesFromDb
        )

        return recipeRepository.save(recipe)
    }

    fun saveNewIngredientsIfNotExists(ingredientNames: List<String>) {
        ingredientNames
            .filter { !ingredientRepository.existsByName(it) }
            .forEach {
                ingredientRepository.save(
                    Ingredient(id = ObjectId.get(), name = it, description = "No Description")
                )
            }
    }

    fun getRecipe(request: GetRecipeRequest): List<Recipe> {

        val categoriesFromDb: List<Category> = request.categoryNames
            .filter { categoryRepository.existsByName(it) }
            .map { categoryRepository.findCategoryByName(it) }
        val recipeName: String = request.recipeName.lowercase()
        val time: Int = request.time

        // Will only search for name of the recipe
        if (categoriesFromDb.isEmpty() && time == 0 && recipeName.isNotEmpty()) {
            return recipeRepository.findRecipesByNameContaining(recipeName)
        }

        // Will only search with categories
        if (recipeName.isEmpty() && time == 0 && categoriesFromDb.isNotEmpty()) {
            return recipeRepository.findRecipesByCategoriesContaining(categoriesFromDb)
        }

        // Will only search for time
        if (categoriesFromDb.isEmpty() && recipeName.isEmpty() && time != 0) {
            return recipeRepository.findRecipesByTime(time)
        }

        // Will search for name and categories
        if (time == 0 && categoriesFromDb.isNotEmpty() && recipeName.isNotEmpty()) {
            return recipeRepository.findRecipesByNameContainingAndCategoriesContaining(recipeName, categoriesFromDb)
        }

        // Will search for name and time
        if (categoriesFromDb.isEmpty() && recipeName.isNotEmpty() && time != 0) {
            return recipeRepository.findRecipesByNameContainingAndTime(recipeName, time)
        }

        // Will search for categories and time
        if (recipeName.isEmpty() && categoriesFromDb.isNotEmpty() && time != 0) {
            return recipeRepository.findRecipesByCategoriesContainingAndTime(categoriesFromDb, time)
        }

        // Will search for all three fields
        if (recipeName.isNotEmpty() && categoriesFromDb.isNotEmpty() && time != 0) {
            return recipeRepository.findRecipesByNameContainingAndCategoriesContainingAndTime(
                recipeName,
                categoriesFromDb,
                time
            )
        }
        return listOf()
    }

    fun suggest(ingredients: List<Ingredient>): List<Recipe> {

        data class RankedRecipe(val ranking: Int, val recipe: Recipe)

        val allRecipes = recipeRepository.findAll()
        val rankedRecipes = mutableListOf<RankedRecipe>()

        allRecipes.forEach {
            var count = 0
            it.ingredients.map { ingredient -> ingredient.name.lowercase() }
                .forEach { name ->
                    count =
                        if (ingredients.map { ingredient -> ingredient.name.lowercase() }.contains(name.lowercase()))
                            count + 1
                        else count
                }

            if (count > 0)
                rankedRecipes.add(RankedRecipe(count, it))
        }

        return rankedRecipes.sortedByDescending { it.ranking }
            .map { it.recipe }
    }
}
