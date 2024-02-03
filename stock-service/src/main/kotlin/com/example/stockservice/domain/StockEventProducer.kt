package com.example.stockservice.domain

interface StockEventProducer {
    fun sendMessage(message: StockUpdatedMessage)
}