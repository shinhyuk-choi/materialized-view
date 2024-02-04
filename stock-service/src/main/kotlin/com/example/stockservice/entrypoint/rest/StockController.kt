package com.example.stockservice.entrypoint.rest

import com.example.stockservice.domain.StockInfo
import com.example.stockservice.domain.UpdateStockCommand
import com.example.stockservice.domain.StockService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1")
class StockController(
    private val stockService: StockService,
) {

    @PatchMapping("stocks")
    fun update(@RequestBody request: UpdateStockCommand): ResponseEntity<Unit> {
        stockService.updateStocks(request)
        return ResponseEntity(HttpStatus.OK)
    }

    //@GetMapping("stocks")
    //fun getStocks(@RequestParam productOptionIds: List<Long>): ResponseEntity<List<StockInfo>> {
    //    val stockInfos = stockService.getStocks(productOptionIds)
    //    return ResponseEntity(stockInfos, HttpStatus.OK)
    //}
}