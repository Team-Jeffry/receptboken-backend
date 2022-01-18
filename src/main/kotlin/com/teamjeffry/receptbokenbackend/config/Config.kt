package com.teamjeffry.receptbokenbackend.config

import com.teamjeffry.receptbokenbackend.category.Category
import com.teamjeffry.receptbokenbackend.category.CategoryRepository
import com.teamjeffry.receptbokenbackend.ingredient.Ingredient
import com.teamjeffry.receptbokenbackend.ingredient.IngredientRepository
import com.teamjeffry.receptbokenbackend.recipe.Recipe
import com.teamjeffry.receptbokenbackend.recipe.RecipeRepository
import org.bson.types.ObjectId
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.stereotype.Component

@Configuration
@Component
class Config {

    @Bean
    fun loadCategories(categoryRepository: CategoryRepository): String {
        categoryRepository.deleteAll()
        categoryRepository.save(Category(ObjectId.get(), "Frukt", "Närodlad och ekologisk"))
        categoryRepository.save(Category(ObjectId.get(), "Frukost", "Gott på morgonen!"))
        categoryRepository.save(Category(ObjectId.get(), "Mellanmål", "Goda mellanmål!"))
        return "Created Categories"
    }

    @Bean
    fun loadIngredients(ingredientRepository: IngredientRepository): String {
        ingredientRepository.deleteAll();

        ingredientRepository.save(Ingredient(ObjectId.get(), name = "Äpple", description = "Goda, söta, runda, röda eller gröna"))
        ingredientRepository.save(Ingredient(ObjectId.get(), name = "Apelsin", description = "Rund och orange"))
        ingredientRepository.save(Ingredient(ObjectId.get(), name = "Banan", description = "Gul och avlång"))
        ingredientRepository.save(Ingredient(ObjectId.get(), name = "Jordgubbar", description = "Små, röda och söta"))
        ingredientRepository.save(Ingredient(ObjectId.get(), name = "Havregryn", description = "Bra till gröt"))
        ingredientRepository.save(Ingredient(ObjectId.get(), name = "Salt", description = "Krydda"))
        return "Created Ingredients"
    }

    @Bean
    fun loadRecipes(
        recipeRepository: RecipeRepository,
        ingredientRepository: IngredientRepository,
        categoryRepository: CategoryRepository
    ): String {
        recipeRepository.deleteAll()
        recipeRepository.save(
            Recipe(
                ObjectId.get(),
                name = "Fruktsallad med jordgubbar och banan",
                description = "Enkel Fruktsallad med Jordgubbar och Banan",
                instruction = "Hacka frukten och blanda i en skål.",
                time = 15, ingredients = listOf(
                    ingredientRepository.findIngredientByName("Apelsin"),
                    ingredientRepository.findIngredientByName("Banan"),
                    ingredientRepository.findIngredientByName("Äpple"),
                    ingredientRepository.findIngredientByName("Jordgubbar")
                ),
                categories = listOf(
                    categoryRepository.findCategoryByName("Frukt")
                )
            )
        )

        recipeRepository.save(
            Recipe(
                ObjectId.get(),
                name = "Fruktsallad med banan",
                description = "Enkel Fruktsallad med Banan",
                instruction = "Hacka frukten och blanda i en skål.",
                time = 13, ingredients = listOf(
                    ingredientRepository.findIngredientByName("Apelsin"),
                    ingredientRepository.findIngredientByName("Banan"),
                    ingredientRepository.findIngredientByName("Äpple"),
                ),
                categories = listOf(
                    categoryRepository.findCategoryByName("Frukt")
                )
            )
        )

        recipeRepository.save(
            Recipe(
                ObjectId.get(),
                name = "Havregrynsgröt",
                description = "Enkel snabb frukost",
                instruction = "Koka försiktigt upp havregrynen i en kastrull med en nypa salt",
                time = 10, ingredients = listOf(
                    ingredientRepository.findIngredientByName("Havregryn"),
                ),
                categories = listOf(
                    categoryRepository.findCategoryByName("Frukost"),
                    categoryRepository.findCategoryByName("Mellanmål")
                )
            )
        )

        return "Created Recipes"
    }
}