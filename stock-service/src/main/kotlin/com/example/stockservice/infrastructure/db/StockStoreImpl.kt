package com.example.stockservice.infrastructure.db

import com.example.stockservice.domain.Stock
import com.example.stockservice.domain.StockStore
import org.springframework.stereotype.Component

@Component
class StockStoreImpl(
    private val stockRepository: StockRepository,
): StockStore {
    override fun saveAll(stocks: List<Stock>): List<Stock> {
        return stockRepository.saveAll(stocks)
    }
}