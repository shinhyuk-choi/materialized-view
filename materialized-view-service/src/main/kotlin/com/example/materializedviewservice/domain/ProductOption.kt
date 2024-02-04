package com.example.materializedviewservice.domain

import com.fasterxml.jackson.annotation.JsonProperty

data class ProductOption(
    @JsonProperty("id") val id: Long,
    @JsonProperty("name") val name: String,
    @JsonProperty("ordering") val ordering: Int,
    @JsonProperty("quantity") var quantity: Int,
)
