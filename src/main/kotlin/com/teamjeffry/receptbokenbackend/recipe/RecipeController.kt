package com.teamjeffry.receptbokenbackend.recipe

import com.teamjeffry.receptbokenbackend.recipe.dto.FindRecipeRequest
import com.teamjeffry.receptbokenbackend.recipe.dto.SaveRecipeRequest
import org.springframework.web.bind.annotation.*

@CrossOrigin("http://localhost:3000/")
@RestController
@RequestMapping("v1/recipe")
class RecipeController(
    private val recipeService: RecipeService
) {

    @PostMapping("/save")
    fun save(@RequestBody request: SaveRecipeRequest): Recipe {
        return recipeService.save(request)
    }

    @PostMapping("/get")
    fun getByName(@RequestBody request: FindRecipeRequest): List<Recipe> {
        return (recipeService.findRecipe(request))
    }
}