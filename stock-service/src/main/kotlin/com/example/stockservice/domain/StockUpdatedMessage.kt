package com.example.stockservice.domain

data class StockUpdatedMessage(
    val productOptions: List<ProductOption>,
){
    data class ProductOption(
        val id: Long,
        val quantity: Int,
    )

    companion object {
        fun fromEntities(stocks: List<Stock>): StockUpdatedMessage {
            val productOptions = stocks.map {
                ProductOption(
                    id = it.productOptionId,
                    quantity = it.quantity,
                )
            }
            return StockUpdatedMessage(
                productOptions = productOptions,
            )
        }
    }
}
