package com.teamjeffry.receptbokenbackend.recipe

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class RecipeController(
    private val recipeRepository: RecipeRepository
) {

    @PostMapping("/v1/recipe/save")
    fun save(@RequestBody request: Recipe): Recipe {
        return recipeRepository.save(request)
    }
}