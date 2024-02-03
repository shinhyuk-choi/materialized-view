package com.example.productservice.infrastructure

import com.example.productservice.domain.ProductReader
import com.example.productservice.domain.entity.Product
import org.springframework.stereotype.Component

@Component
class ProductReaderImpl(
    private val productRepository: ProductRepository,
): ProductReader {
    override fun findAllById(ids: List<Long>): MutableList<Product> {
        return productRepository.findAllById(ids)
    }
}