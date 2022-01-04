package com.teamjeffry.receptbokenbackend.category

import org.springframework.data.mongodb.repository.MongoRepository

interface CategoryRepository : MongoRepository<Category, String> {
    fun save(category: Category): Category
    fun findCategoryByName(name: String): Category
}