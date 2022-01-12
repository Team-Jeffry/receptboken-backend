package com.teamjeffry.receptbokenbackend.recipe

class RecipeService(
    private val recipeRepository: RecipeRepository
) {
    fun save(request: Recipe): Recipe {
        println("Saved!")
        return recipeRepository.save(request)
    }

    fun findRecipeByName(name: String): Recipe {
        return recipeRepository.findRecipeByName(name)
    }


}