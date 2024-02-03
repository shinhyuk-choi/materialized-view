package com.example.stockservice.domain

interface StockStore {
    fun saveAll(stocks: List<Stock>): List<Stock>
}
