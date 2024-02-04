package com.example.materializedviewservice.entrypoint.event.dto

import com.fasterxml.jackson.annotation.JsonProperty


data class StockUpdateDto (
    @JsonProperty("productOptions") val productOptions: List<ProductOption>
){
    data class ProductOption(
        @JsonProperty("id") val id: Long,
        @JsonProperty("quantity") val quantity: Int,
        @JsonProperty("productId") val productId: Long,
        @JsonProperty("productOptionGroupId") val productOptionGroupId: Long,
    )
}