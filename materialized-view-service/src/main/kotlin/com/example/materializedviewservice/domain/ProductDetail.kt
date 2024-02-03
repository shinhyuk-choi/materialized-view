package com.example.materializedviewservice.domain

import com.example.common.BaseEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.hibernate.annotations.JdbcTypeCode
import org.hibernate.type.SqlTypes


@Entity
@Table(name = "product_detail_pages")
class ProductDetail constructor(
    var name: String,
    var originalPrice: Long,
    @Column(name = "product_option_groups", columnDefinition = "json")
    @JdbcTypeCode(SqlTypes.JSON)
    var productOptionGroups: MutableList<ProductOptionGroup> = mutableListOf()
) : BaseEntity() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L


}