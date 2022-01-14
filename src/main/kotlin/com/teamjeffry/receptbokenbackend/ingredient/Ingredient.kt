package com.teamjeffry.receptbokenbackend.ingredient

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonInclude
import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Document
data class Ingredient(
    @Id
    val id: ObjectId = ObjectId.get(),
    val name: String,
    val description: String?,
)
