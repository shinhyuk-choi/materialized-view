package com.example.materializedviewservice.domain.dto

import com.example.materializedviewservice.domain.ProductOptionGroup
import com.fasterxml.jackson.annotation.JsonProperty


data class ProductUpsertDto (
    @JsonProperty("id") val id: Long,
    @JsonProperty("name") val name: String,
    @JsonProperty("price") val price: Long,
    @JsonProperty("productOptionGroups") val productOptionGroups: MutableList<ProductOptionGroup> = mutableListOf()
)