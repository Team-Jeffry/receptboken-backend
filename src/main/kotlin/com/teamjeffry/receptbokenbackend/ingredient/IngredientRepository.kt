package com.teamjeffry.receptbokenbackend.ingredient

import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface IngredientRepository : MongoRepository<Ingredient, String> {
    fun save(ingredient: Ingredient): Ingredient
    fun findIngredientByName(name: String): Ingredient
    fun existsByName(name: String): Boolean
}