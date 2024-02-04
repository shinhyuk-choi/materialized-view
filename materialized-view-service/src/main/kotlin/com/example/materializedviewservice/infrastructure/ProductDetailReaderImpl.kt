package com.example.materializedviewservice.infrastructure

import com.example.materializedviewservice.domain.ProductDetail
import com.example.materializedviewservice.domain.ProductDetailReader
import org.springframework.stereotype.Component
import java.util.*

@Component
class ProductDetailReaderImpl(
    private val productDetailRepository: ProductDetailRepository
): ProductDetailReader {
    override fun findById(id: Long): Optional<ProductDetail> {
        return productDetailRepository.findById(id)
    }
}