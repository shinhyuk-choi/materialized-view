package com.example.stockservice.infrastructure.db

import com.example.stockservice.domain.Stock
import jakarta.persistence.LockModeType
import jakarta.persistence.QueryHint
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Lock
import org.springframework.data.jpa.repository.QueryHints

interface StockRepository : JpaRepository<Stock, Long> {
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @QueryHints(value = [QueryHint(name = "javax.persistence.lock.timeout", value = "5000")])
    fun findAllByProductOptionIdIn(productOptionIds: List<Long>): List<Stock>
}