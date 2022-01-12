package com.teamjeffry.receptbokenbackend.recipe

import com.teamjeffry.receptbokenbackend.category.CategoryRepository
import org.springframework.stereotype.Service

@Service
class RecipeService(
    private val recipeRepository: RecipeRepository,
    private val categoryRepository: CategoryRepository
) {
    fun save(request: Recipe): Recipe {
        return recipeRepository.save(request)
    }

    fun findRecipe(request: RecipeDTO): List<Recipe> {
        if (request.categoryName.isBlank() && request.time == 0 && request.recipeName.isNotBlank()) {
            return listOf(recipeRepository.findRecipeByName(request.recipeName))
        }
        if (request.recipeName.isBlank() && request.categoryName.isBlank() && request.time != 0) {
            return recipeRepository.findRecipesByTime(request.time)
        }
        if (request.recipeName.isBlank() && request.time == 0 && request.categoryName.isNotBlank()) {
            return recipeRepository.findRecipesByCategories(listOf(categoryRepository.findCategoryByName(request.categoryName)))
        }
        return recipeRepository.findRecipesByNameAndCategoriesAndTime(request.recipeName, listOf(categoryRepository.findCategoryByName(request.categoryName)), request.time)
    }


}