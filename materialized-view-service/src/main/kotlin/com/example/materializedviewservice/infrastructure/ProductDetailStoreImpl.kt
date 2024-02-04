package com.example.materializedviewservice.infrastructure

import com.example.materializedviewservice.domain.ProductDetail
import com.example.materializedviewservice.domain.ProductDetailStore
import org.springframework.stereotype.Component

@Component
class ProductDetailStoreImpl(
    private val productDetailRepository: ProductDetailRepository,
): ProductDetailStore {
    override fun save(productDetail: ProductDetail): ProductDetail {
        return productDetailRepository.save(productDetail)
    }

    override fun saveAll(productDetails: List<ProductDetail>): List<ProductDetail> {
        return productDetailRepository.saveAll(productDetails)
    }
}