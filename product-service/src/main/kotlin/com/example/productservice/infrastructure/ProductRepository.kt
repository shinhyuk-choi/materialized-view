package com.example.productservice.infrastructure

import com.example.productservice.domain.entity.Product
import org.springframework.data.jpa.repository.JpaRepository

interface ProductRepository: JpaRepository<Product, Long> {

}