package com.example.productservice.infrastructure

import com.example.productservice.domain.ProductStore
import com.example.productservice.domain.entity.Product
import org.springframework.stereotype.Component

@Component
class ProductStoreImplProductStore(
    private val productRepository: ProductRepository,
): ProductStore {

    override fun saveAll(products: List<Product>): List<Product> {
        return productRepository.saveAll(products)
    }
}