package com.example.productservice.domain.dto

data class RegisterProductCommand(
    val name: String,
    val description: String,
    val price: Long,
    val productOptionGroups: List<ProductOptionGroup>,
){
    data class ProductOptionGroup(
        val name: String,
        val ordering: Int,
        val productOptions: List<ProductOption>
    )

    data class ProductOption(
        val name: String,
        val price: Long,
        val ordering: Int,
    )
}

