package com.example.productservice.domain.entity

import com.example.common.BaseEntity
import jakarta.persistence.CascadeType
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.OneToMany
import jakarta.persistence.Table

@Entity
@Table(name = "products")
class Product constructor(
    var name: String,
    var price: Long,
) : BaseEntity() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L
    var salesStatus: SalesStatus = SalesStatus.ON_SALE

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY, cascade = [CascadeType.PERSIST])
    val optionGroups: MutableList<ProductOptionGroup> = mutableListOf()


    companion object {
        fun generate(
            name: String,
            price: Long,
        ): Product {
            return Product(
                name = name,
                price = price,
            )
        }
    }

    fun changeSalesStatus(salesStatus: SalesStatus?) {
        if (salesStatus != null) {
            this.salesStatus = salesStatus
        }
    }
}