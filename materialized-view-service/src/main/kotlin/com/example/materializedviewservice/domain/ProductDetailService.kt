package com.example.materializedviewservice.domain

import com.example.materializedviewservice.entrypoint.event.dto.ProductUpsertDto
import com.example.materializedviewservice.entrypoint.event.dto.StockUpdateDto
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

    fun updateStock(dto: StockUpdateDto) {
        val productIds = dto.productOptions.map { it.productId }
        val productDetails = productDetailReader.findAllById(productIds)
        val productOptions = productDetails.flatMap { it.productOptionGroups }.flatMap { it.productOptions }
        val productOptionIdToProductOption = productOptions.associateBy { it.id }
        for (dtoProductOption in dto.productOptions) {
            val productOption = productOptionIdToProductOption[dtoProductOption.id] ?: continue
            productOption.quantity = dtoProductOption.quantity
        }
        productDetailStore.saveAll(productDetails)
    }
}