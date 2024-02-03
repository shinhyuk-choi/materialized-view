package com.example.productservice.domain.dto

import com.example.productservice.domain.entity.SalesStatus

data class ChangeSalesStatusCommand(
    val products: List<Product>
){
    data class Product(
        val id: Long,
        val salesStatus: SalesStatus,
        val productOptions: List<ProductOption>
    )

    data class ProductOption(
        val id: Long,
        val salesStatus: SalesStatus
    )
}
