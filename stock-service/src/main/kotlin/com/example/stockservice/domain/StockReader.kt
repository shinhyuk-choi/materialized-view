package com.example.stockservice.domain

interface StockReader {
    fun findAllByProductOptionIdIn(productOptionIds: List<Long>): List<Stock>
}
