package com.example.stockservice.domain

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "stocks")
class Stock constructor(
    var quantity: Int,
    @Id
    val productOptionId: Long,
    val productId: Long,
    val productOptionGroupId: Long,
) {


    companion object {
        fun generate(
            quantity: Int,
            productOptionId: Long,
            productId: Long,
            productOptionGroupId: Long,
        ): Stock {
            return Stock(
                quantity = quantity,
                productOptionId = productOptionId,
                productId = productId,
                productOptionGroupId = productOptionGroupId,
            )
        }
    }

    fun increaseOrDecreaseQuantity(changeQuantity: Int) {
        this.quantity += changeQuantity
    }

}
