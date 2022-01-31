package com.teamjeffry.receptbokenbackend.category

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@CrossOrigin
@RequestMapping("/v1/category")
class CategoryController(
    private val categoryRepository: CategoryRepository
) {

    @GetMapping("/all")
    fun getAllCategories(): ResponseEntity<List<Category>> {
        return ResponseEntity.ok(categoryRepository.findAll());
    }
}