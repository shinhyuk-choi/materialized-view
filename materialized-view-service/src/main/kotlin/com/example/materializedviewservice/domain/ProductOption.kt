package com.example.materializedviewservice.domain

import com.fasterxml.jackson.annotation.JsonProperty

data class ProductOption(
    @JsonProperty("name") val name: String,
    @JsonProperty("ordering") val ordering: Int,
)
