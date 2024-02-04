package com.example.materializedviewservice.infrastructure

import com.example.materializedviewservice.domain.ProductDetail
import org.springframework.data.jpa.repository.JpaRepository

interface ProductDetailRepository: JpaRepository<ProductDetail, Long> {
}