package com.teamjeffry.receptbokenbackend.recipe

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

    @GetMapping("/get/{name}")
    fun getByName(@PathVariable name: String): Recipe {
        return recipeService.findRecipeByName(name);
    }
}