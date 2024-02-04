package com.example.materializedviewservice.entrypoint.event

import com.example.materializedviewservice.domain.ProductDetailService
import com.example.materializedviewservice.entrypoint.event.dto.ProductUpsertDto
import com.example.materializedviewservice.entrypoint.event.dto.StockUpdateDto
import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component
import java.util.concurrent.ConcurrentHashMap

@Component
class StockConsumer(
    private val productDetailService: ProductDetailService
) {
    private val objectMapper = ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
    private val idHistoryMap: MutableMap<String, Int> = ConcurrentHashMap()

    @KafkaListener(topics = ["stock-service-topic"], groupId = "stock-consumer-group", concurrency = "1")
    @Throws(JsonProcessingException::class)
    fun listen(message: ConsumerRecord<String, String>) {
        val dto = objectMapper.readValue(message.value(), StockUpdateDto::class.java)
        productDetailService.updateStock(dto)
    }
}