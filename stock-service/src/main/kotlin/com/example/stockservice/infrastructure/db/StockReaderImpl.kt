package com.example.stockservice.infrastructure.db

import com.example.stockservice.domain.Stock
import com.example.stockservice.domain.StockReader
import org.springframework.stereotype.Component

@Component
class StockReaderImpl(
    private val stockRepository: StockRepository,
) : StockReader {
    override fun findAllByProductOptionIdIn(productOptionIds: List<Long>): List<Stock> {
        return stockRepository.findAllByProductOptionIdIn(productOptionIds)
    }
}