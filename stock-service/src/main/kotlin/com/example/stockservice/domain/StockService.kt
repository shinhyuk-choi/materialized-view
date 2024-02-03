package com.example.stockservice.domain

import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class StockService(
    private val stockReader: StockReader,
    private val stockStore: StockStore,
    private val stockEventProducer: StockEventProducer,
) {

    @Transactional
    fun updateStocks(command: UpdateStockCommand) {
        val productOptionIds = command.productOptions.map { it.id }
        val stocksExists = stockReader.findAllByProductOptionIdIn(productOptionIds)
        val stocksCreated: MutableList<Stock> = generateNotExistsStocks(stocksExists, productOptionIds)
        val stocks: List<Stock> = (stocksExists + stocksCreated)
        val productOptionIdToChangeQuantityMap = command.productOptions.associateBy { it.id }
        stocks.forEach {
            val changeQuantity = productOptionIdToChangeQuantityMap[it.productOptionId]?.changeQuantity ?: 0
            it.increaseOrDecreaseQuantity(changeQuantity)
        }
        stockStore.saveAll(stocks)

        stockEventProducer.sendMessage(
            StockUpdatedMessage.fromEntities(stocks)
        )
    }

    @Transactional
    fun getStocks(productOptionIds: List<Long>): List<StockInfo> {
        val stocksExists = stockReader.findAllByProductOptionIdIn(productOptionIds)
        val stocksCreated: MutableList<Stock> = generateNotExistsStocks(stocksExists, productOptionIds)
        val stockInfos: List<StockInfo> = (stocksExists + stocksCreated)
            .map { StockInfo(it.productOptionId, it.quantity) }
        return stockInfos

    }

    private fun generateNotExistsStocks(
        stocks: List<Stock>,
        productOptionIds: List<Long>
    ): MutableList<Stock> {
        val productOptionIdsOfStocks: HashSet<Long> = stocks.map { it.productOptionId }.toHashSet()
        val stocksToCreate: MutableList<Stock> = mutableListOf()
        for (productOptionId in productOptionIds) {
            if (!productOptionIdsOfStocks.contains(productOptionId)) {
                stocksToCreate.add(Stock(productOptionId = productOptionId, quantity = 0))
            }
        }
        return stocksToCreate
    }
}