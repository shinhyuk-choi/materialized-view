package com.example.stockservice.domain

data class UpdateStockCommand(
    val productOptions: List<ProductOption>
){
    data class ProductOption(
        val id: Long,
        val changeQuantity: Int,
    )
}
