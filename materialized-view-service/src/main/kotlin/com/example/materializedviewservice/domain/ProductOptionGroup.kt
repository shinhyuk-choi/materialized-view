package com.example.materializedviewservice.domain

import com.fasterxml.jackson.annotation.JsonProperty

data class ProductOptionGroup(
    @JsonProperty("name") val name: String,
    @JsonProperty("ordering") val ordering: Int,
    @JsonProperty("productOptions") val productOptions: MutableList<ProductOption> = mutableListOf()
)
