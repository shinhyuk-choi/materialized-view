package com.example.materializedviewservice.domain

import com.example.common.BaseEntity
import com.example.materializedviewservice.entrypoint.event.dto.ProductUpsertDto
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.hibernate.annotations.JdbcTypeCode
import org.hibernate.type.SqlTypes


@Entity
@Table(name = "product_details")
class ProductDetail constructor(
    var name: String,
    var originalPrice: Long,
    @Id val id: Long,
    @Column(name = "product_option_groups", columnDefinition = "json")
    @JdbcTypeCode(SqlTypes.JSON)
    var productOptionGroups: MutableList<ProductOptionGroup> = mutableListOf()
) : BaseEntity() {

    fun update(dto: ProductUpsertDto) {
        this.name = dto.name
        this.originalPrice = dto.price
        this.productOptionGroups = dto.productOptionGroups
    }

    companion object {
        fun generate(dto: ProductUpsertDto): ProductDetail {
            return ProductDetail(
                id = dto.id,
                name = dto.name,
                originalPrice = dto.price,
                productOptionGroups = dto.productOptionGroups
            )
        }
    }
}