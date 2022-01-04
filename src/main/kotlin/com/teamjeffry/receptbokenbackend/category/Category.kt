package com.teamjeffry.receptbokenbackend.category

import com.fasterxml.jackson.annotation.JsonInclude
import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Document
data class Category(
    @Id
    val id: ObjectId = ObjectId.get(),
    val name: String,
    val description: String,
)
