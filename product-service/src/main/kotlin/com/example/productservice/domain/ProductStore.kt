package com.example.productservice.domain

import com.example.productservice.domain.entity.Product

interface ProductStore {
    fun saveAll(products: List<Product>): List<Product>
}