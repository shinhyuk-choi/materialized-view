package com.example.productservice.application.usecase

import com.example.productservice.domain.ProductService
import com.example.productservice.domain.dto.ChangeSalesStatusCommand
import org.springframework.stereotype.Component

@Component
class ChangeSalesStatusUseCase(
    private val productService: ProductService
) {
    fun changeSalesStatus(
        command: ChangeSalesStatusCommand
    ) {
        productService.changeSalesStatus(
            command = command
        )
    }
}