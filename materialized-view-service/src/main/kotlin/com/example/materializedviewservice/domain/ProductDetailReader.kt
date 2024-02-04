package com.example.materializedviewservice.domain

import java.util.*

interface ProductDetailReader {
    fun findById(id: Long): Optional<ProductDetail>

    fun findAllById(ids: List<Long>): List<ProductDetail>
}