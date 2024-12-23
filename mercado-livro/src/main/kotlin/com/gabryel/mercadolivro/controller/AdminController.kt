package com.gabryel.mercadolivro.controller

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/admin")
class AdminController {

    @GetMapping("/report")
    @ResponseStatus(HttpStatus.OK)
    fun admin() : String {
        return "Eu sou o admin do sistema"
    }

}