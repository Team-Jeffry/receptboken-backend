package com.teamjeffry.receptbokenbackend.recipe

import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("v1/recipe")
class RecipeController(
    private val recipeRepository: RecipeRepository
) {

    @PostMapping("/save")
    fun save(@RequestBody request: Recipe): Recipe {
        return recipeRepository.save(request)
    }

    @GetMapping("/get/{name}")
    fun getByName(@PathVariable name: String): Recipe {
        return recipeRepository.findRecipeByName(name);
    }
}