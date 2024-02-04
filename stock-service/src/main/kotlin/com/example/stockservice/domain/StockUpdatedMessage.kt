package com.example.stockservice.domain

data class StockUpdatedMessage(
    val productOptions: List<ProductOption>,
){
    data class ProductOption(
        val id: Long,
        val quantity: Int,
        val productId: Long,
        val productOptionGroupId: Long,
    )

    companion object {
        fun fromEntities(stocks: List<Stock>): StockUpdatedMessage {
            val productOptions = stocks.map {
                ProductOption(
                    id = it.productOptionId,
                    quantity = it.quantity,
                    productId = it.productId,
                    productOptionGroupId = it.productOptionGroupId,
                )
            }
            return StockUpdatedMessage(
                productOptions = productOptions,
            )
        }
    }
}
