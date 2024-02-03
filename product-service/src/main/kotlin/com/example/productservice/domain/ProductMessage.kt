package com.example.productservice.domain

import com.example.productservice.domain.entity.Product
import com.example.productservice.domain.entity.SalesStatus

data class ProductMessage(
    val id: Long,
    val name: String,
    val price: Int,
    val salesStatus: SalesStatus,
    val productOptionGroups: List<ProductOptionGroup>,
){
    data class ProductOptionGroup(
        val id: Long,
        val name: String,
        val productOptions: List<ProductOption>,
    ){
        data class ProductOption(
            val id: Long,
            val name: String,
            val price: Int,
            val salesStatus: SalesStatus,
        )
    }

    companion object {
        fun generate(
            product: Product,
        ): ProductMessage {
            return ProductMessage(
                id = product.id,
                name = product.name,
                price = product.price.toInt(),
                salesStatus = product.salesStatus,
                productOptionGroups = product.optionGroups.map { optionGroup ->
                    ProductOptionGroup(
                        id = optionGroup.id,
                        name = optionGroup.name,
                        productOptions = optionGroup.options.map { option ->
                            ProductOptionGroup.ProductOption(
                                id = option.id,
                                name = option.name,
                                price = option.price.toInt(),
                                salesStatus = option.salesStatus,
                            )
                        }
                    )
                }
            )
        }
    }
}

