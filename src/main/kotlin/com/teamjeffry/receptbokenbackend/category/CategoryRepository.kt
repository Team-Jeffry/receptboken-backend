package com.teamjeffry.receptbokenbackend.category

import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface CategoryRepository : MongoRepository<Category, String> {
    fun save(category: Category): Category
    fun findCategoryByName(name: String): Category
    fun existsByName(name: String): Boolean
}