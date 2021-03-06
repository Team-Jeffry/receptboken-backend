package com.teamjeffry.receptbokenbackend.recipe

import com.teamjeffry.receptbokenbackend.recipe.dto.GetRecipeRequest
import com.teamjeffry.receptbokenbackend.ingredient.Ingredient
import com.teamjeffry.receptbokenbackend.recipe.dto.SaveRecipeRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@CrossOrigin
@RestController
@RequestMapping("v1/recipe")
class RecipeController(
    private val recipeService: RecipeService
) {

    @PostMapping("/save")
    fun save(@RequestBody request: SaveRecipeRequest): ResponseEntity<Recipe> {
        return ResponseEntity<Recipe>(recipeService.save(request), HttpStatus.OK)
    }

    @PostMapping("/get")
    fun get(@RequestBody request: GetRecipeRequest): ResponseEntity<List<Recipe>> {
        return ResponseEntity<List<Recipe>>(recipeService.getRecipe(request), HttpStatus.OK)
    }

    @PostMapping("/suggest")
    fun suggest(@RequestBody ingredients: List<Ingredient>): ResponseEntity<List<Recipe>> {
        return ResponseEntity<List<Recipe>>(recipeService.suggest(ingredients), HttpStatus.OK)
    }
}