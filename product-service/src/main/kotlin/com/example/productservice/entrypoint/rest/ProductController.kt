package com.example.productservice.entrypoint.rest

import com.example.productservice.application.usecase.ChangeSalesStatusUseCase
import com.example.productservice.application.usecase.RegisterProductUseCase
import com.example.productservice.domain.dto.ChangeSalesStatusCommand
import com.example.productservice.domain.dto.RegisterProductCommand
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1")
class ProductController(
    private val changeSalesStatusUseCase: ChangeSalesStatusUseCase,
    private val registerProductUseCase: RegisterProductUseCase,
) {

    @PostMapping("products")
    fun register(@RequestBody request: RegisterProductCommand): ResponseEntity<Unit> {
        registerProductUseCase.register(request)
        return ResponseEntity(HttpStatus.OK)
    }

    @PatchMapping("products/sales-status")
    fun changeSalesStatus(@RequestBody request: ChangeSalesStatusCommand): ResponseEntity<Unit> {
        changeSalesStatusUseCase.changeSalesStatus(request)
        return ResponseEntity(HttpStatus.OK)
    }


}