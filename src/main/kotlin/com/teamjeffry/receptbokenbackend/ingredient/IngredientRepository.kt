package com.teamjeffry.receptbokenbackend.ingredient

import org.springframework.data.mongodb.repository.MongoRepository

interface IngredientRepository : MongoRepository<Ingredient, String> {
    fun save(ingredient: Ingredient): Ingredient
    fun findIngredientByName(name: String): Ingredient
}