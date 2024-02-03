package com.example.productservice.domain.entity

import com.example.common.BaseEntity
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table

@Entity
@Table(name = "product_options")
class ProductOption constructor(
    var name: String,
    var price: Long,
    var ordering: Int,
    @ManyToOne
    val productOptionGroup: ProductOptionGroup,
): BaseEntity() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L
    var salesStatus: SalesStatus = SalesStatus.ON_SALE


    companion object {
        fun generate(
            name: String,
            price: Long,
            ordering: Int,
            productOptionGroup: ProductOptionGroup,
        ): ProductOption {
            return ProductOption(
                name = name,
                price = price,
                ordering = ordering,
                productOptionGroup = productOptionGroup,
            )
        }
    }

    fun changeSalesStatus(salesStatus: SalesStatus) {
        this.salesStatus = salesStatus
    }
}