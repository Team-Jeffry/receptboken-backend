package com.teamjeffry.receptbokenbackend.recipe

import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("v1/recipe")
class RecipeController(
    private val recipeService: RecipeService
) {

    @PostMapping("/save")
    fun save(@RequestBody request: Recipe): Recipe {
        return recipeService.save(request)
    }

    @PostMapping("/get")
    fun getByName(@RequestBody request: RecipeDTO): List<Recipe> {
        return (recipeService.findRecipe(request))
    }
}