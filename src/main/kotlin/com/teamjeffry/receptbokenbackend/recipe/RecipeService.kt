package com.teamjeffry.receptbokenbackend.recipe

import com.teamjeffry.receptbokenbackend.category.Category
import com.teamjeffry.receptbokenbackend.category.CategoryRepository
import com.teamjeffry.receptbokenbackend.ingredient.Ingredient
import com.teamjeffry.receptbokenbackend.ingredient.IngredientRepository
import com.teamjeffry.receptbokenbackend.recipe.dto.SaveRecipeRequest
import org.bson.types.ObjectId
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class RecipeService(
    private val recipeRepository: RecipeRepository,
    private val categoryRepository: CategoryRepository,
    private val ingredientRepository: IngredientRepository
) {

    @Transactional
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

        return recipeRepository.save(recipe)
    }

    fun findRecipeByName(name: String): Recipe {
        return recipeRepository.findRecipeByName(name)
    }

    fun suggest(ingredients: List<Ingredient>): List<Recipe> {

        val allRecipes = recipeRepository.findAll()
        val rankedRecipies = mutableListOf<RankedRecipe>()

        allRecipes.forEach {
            var count = 0
            it.ingredients.map { ingredient ->  ingredient.name.lowercase() }
                .forEach { name ->
                    count = if (ingredients.map { ingredient -> ingredient.name.lowercase() }.contains(name.lowercase()))
                        count + 1
                    else count
                }

            if (count > 0)
                rankedRecipies.add(RankedRecipe(count, it))
        }

        return rankedRecipies.sortedByDescending { it.ranking }
            .map { it.recipe }
    }
}

data class RankedRecipe(val ranking: Int, val recipe: Recipe)