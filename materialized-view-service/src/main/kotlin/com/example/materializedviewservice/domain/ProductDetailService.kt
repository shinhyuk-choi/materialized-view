package com.example.materializedviewservice.domain

import com.example.materializedviewservice.domain.dto.ProductUpsertDto
import org.springframework.stereotype.Service

@Service
class ProductDetailService(
    private val productDetailReader: ProductDetailReader,
    private val productDetailStore: ProductDetailStore,
) {
    fun upsertProduct(dto: ProductUpsertDto) {
        try{
            val productDetail = productDetailReader.findById(dto.id).get()
            productDetail.update(dto)
        } catch (e: NoSuchElementException) {
            val productDetail = ProductDetail.generate(dto)
            productDetailStore.save(productDetail)
        }

    }
}