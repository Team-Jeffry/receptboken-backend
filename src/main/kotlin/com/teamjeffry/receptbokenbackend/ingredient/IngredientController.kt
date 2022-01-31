package com.teamjeffry.receptbokenbackend.ingredient

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@CrossOrigin
@RequestMapping("/v1/ingredient")
class IngredientController(
    private val ingredientRepository: IngredientRepository
) {

    @GetMapping("/all")
    fun getAllIngredients(): ResponseEntity<List<Ingredient>> {
        return ResponseEntity.ok(ingredientRepository.findAll());
    }
}