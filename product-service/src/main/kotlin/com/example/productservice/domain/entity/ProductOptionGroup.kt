package com.example.productservice.domain.entity

import com.example.common.BaseEntity
import jakarta.persistence.CascadeType
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.ManyToOne
import jakarta.persistence.OneToMany
import jakarta.persistence.Table

@Entity
@Table(name = "product_option_groups")
class ProductOptionGroup constructor(
    val name: String,
    val ordering: Int,
    @ManyToOne
    val product: Product,
): BaseEntity() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L

    @OneToMany(mappedBy = "productOptionGroup", fetch = FetchType.LAZY, cascade = [CascadeType.PERSIST])
    val options: MutableList<ProductOption> = mutableListOf()

    companion object {
        fun generate(
            name: String,
            ordering: Int,
            product: Product,
        ): ProductOptionGroup {
            return ProductOptionGroup(
                name = name,
                ordering = ordering,
                product = product,
            )
        }
    }
}