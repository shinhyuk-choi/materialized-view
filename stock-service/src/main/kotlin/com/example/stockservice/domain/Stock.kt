package com.example.stockservice.domain

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "stocks")
class Stock constructor(
    var quantity: Int,
    @Id
    val productOptionId: Long,
) {
    //@Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    //val id: Long = 0L

    companion object {
        fun generate(
            quantity: Int,
            productOptionId: Long,
        ): Stock {
            return Stock(
                quantity = quantity,
                productOptionId = productOptionId,
            )
        }
    }

    fun increaseOrDecreaseQuantity(changeQuantity: Int) {
        this.quantity += changeQuantity
    }

}
