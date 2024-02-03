package com.example.productservice.domain

interface ProductEventProducer {
    fun sendMessage(message: ProductMessage)
}