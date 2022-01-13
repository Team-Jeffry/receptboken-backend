package com.teamjeffry.receptbokenbackend

import com.teamjeffry.receptbokenbackend.category.Category
import com.teamjeffry.receptbokenbackend.category.CategoryRepository
import org.bson.types.ObjectId
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.web.client.RestTemplate

@SpringBootApplication
class ReceptbokenBackendApplication {

    @Bean
    fun run(categoryRepository: CategoryRepository): CommandLineRunner {
        return CommandLineRunner { args ->
            
            categoryRepository.deleteAll()

            categoryRepository.save(Category(ObjectId.get(), "Frukt", "Närodlad och ekologisk"))
            categoryRepository.save(Category(ObjectId.get(), "Frukost", "Gott på morgonen!"))
            categoryRepository.save(Category(ObjectId.get(), "Mellanmål", "Goda mellanmål!"))
        }
    }
}

fun main(args: Array<String>) {
    runApplication<ReceptbokenBackendApplication>(*args)
}
