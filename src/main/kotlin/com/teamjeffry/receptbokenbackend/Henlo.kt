package com.teamjeffry.receptbokenbackend

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class Henlo {

    @GetMapping("/henlo")
    fun henlo(): String = "henlo"
}