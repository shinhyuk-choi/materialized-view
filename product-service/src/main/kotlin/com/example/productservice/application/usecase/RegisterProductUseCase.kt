package com.example.productservice.application.usecase

import com.example.productservice.domain.ProductService
import com.example.productservice.domain.dto.RegisterProductCommand
import org.springframework.stereotype.Component

@Component
class RegisterProductUseCase(
    private val productService: ProductService,
) {
    fun register(command: RegisterProductCommand) {
        productService.register(command)
    }
}