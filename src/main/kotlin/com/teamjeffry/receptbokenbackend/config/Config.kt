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
        categoryRepository.save(Category(ObjectId.get(), "Vegetariskt", "Mat för vegetarianer"))
        categoryRepository.save(Category(ObjectId.get(), "Laktosfritt", "Laktosfri mat"))
        categoryRepository.save(Category(ObjectId.get(), "Middagar", "En perfekt middag!"))
        categoryRepository.save(Category(ObjectId.get(), "Husmanskost", "Svenska klassiker"))
        categoryRepository.save(Category(ObjectId.get(), "Asiatiskt", "Otroligt smakrika och nyttiga rätter!"))
        categoryRepository.save(Category(ObjectId.get(), "Italienskt", "Enkla och goda smaker"))
        categoryRepository.save(Category(ObjectId.get(), "Mexikanskt", "Smakexplosioner!"))
        categoryRepository.save(Category(ObjectId.get(), "Veganskt", "Mat för veganer"))
        categoryRepository.save(Category(ObjectId.get(), "Glutenfritt", "Glutenfria recept"))
        categoryRepository.save(Category(ObjectId.get(), "Amerikanskt", "Bra för själen, dåligt för hjärtat"))
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
        ingredientRepository.save(Ingredient(ObjectId.get(), name = "Svartpeppar", description = "Krydda"))
        ingredientRepository.save(Ingredient(ObjectId.get(), name = "Ris", description = "Bra kolhydrat"))
        ingredientRepository.save(Ingredient(ObjectId.get(), name = "Potatis", description = "God kolhydrat"))
        ingredientRepository.save(Ingredient(ObjectId.get(), name = "Blandfärs", description = "Saftig färs"))
        ingredientRepository.save(Ingredient(ObjectId.get(), name = "Nötfärs", description = "God färs"))
        ingredientRepository.save(Ingredient(ObjectId.get(), name = "Högrev", description = "Smakrikt kött"))
        ingredientRepository.save(Ingredient(ObjectId.get(), name = "Tomat", description = "Saftigt!"))
        ingredientRepository.save(Ingredient(ObjectId.get(), name = "Vitlök", description = "En otroligt viktig ingrediens"))
        ingredientRepository.save(Ingredient(ObjectId.get(), name = "Gullök", description = "Används inom de flesta recepten"))
        ingredientRepository.save(Ingredient(ObjectId.get(), name = "Kycklingbröst", description = "Bäst för bodybuilding"))
        ingredientRepository.save(Ingredient(ObjectId.get(), name = "Kycklinglår", description = "En godare version av kycklingbröst"))
        ingredientRepository.save(Ingredient(ObjectId.get(), name = "Grädde", description = "Förgyller livet"))
        ingredientRepository.save(Ingredient(ObjectId.get(), name = "Smör", description = "Ett måste inom svensk husmanskost"))
        ingredientRepository.save(Ingredient(ObjectId.get(), name = "Gurka", description = "Krispigt och saftigt"))
        ingredientRepository.save(Ingredient(ObjectId.get(), name = "Korv", description = "Gott med korv"))
        ingredientRepository.save(Ingredient(ObjectId.get(), name = "Palsternacka", description = "Bra rotfrukt"))
        ingredientRepository.save(Ingredient(ObjectId.get(), name = "Sötpotatis", description = "Sötpotatis är faktiskt en ört!"))
        ingredientRepository.save(Ingredient(ObjectId.get(), name = "Vattemelon", description = "Vattenmelon är faktiskt ett bär!"))
        ingredientRepository.save(Ingredient(ObjectId.get(), name = "Timjan", description = "God ört"))
        ingredientRepository.save(Ingredient(ObjectId.get(), name = "Oregano", description = "God ört"))
        ingredientRepository.save(Ingredient(ObjectId.get(), name = "Basilika", description = "God ört"))
        ingredientRepository.save(Ingredient(ObjectId.get(), name = "Mynta", description = "God ört"))
        ingredientRepository.save(Ingredient(ObjectId.get(), name = "Dill", description = "God ört"))
        ingredientRepository.save(Ingredient(ObjectId.get(), name = "Salvia", description = "God ört"))
        ingredientRepository.save(Ingredient(ObjectId.get(), name = "Rosmarin", description = "God ört"))
        ingredientRepository.save(Ingredient(ObjectId.get(), name = "Mjölk", description = "Mejeri"))
        ingredientRepository.save(Ingredient(ObjectId.get(), name = "Mjöl", description = "Används inom mycket"))
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