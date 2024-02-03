package com.example.productservice.domain

import com.example.productservice.domain.entity.Product

interface ProductReader {
    fun findAllById(ids: List<Long>): MutableList<Product>
}