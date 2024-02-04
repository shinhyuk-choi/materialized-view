package com.example.materializedviewservice.domain

interface ProductDetailStore {
    fun save(productDetail: ProductDetail): ProductDetail
}