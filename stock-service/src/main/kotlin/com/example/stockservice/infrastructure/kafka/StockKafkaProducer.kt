package com.example.stockservice.infrastructure.kafka

import com.example.common.Topic
import com.example.stockservice.domain.StockEventProducer
import com.example.stockservice.domain.StockUpdatedMessage
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component

@Component
class StockKafkaProducer(
    private val kafkaTemplate: KafkaTemplate<String, Any>,
): StockEventProducer {
    var objectMapper: ObjectMapper = ObjectMapper()
    override fun sendMessage(message: StockUpdatedMessage){
        kafkaTemplate.send(
            Topic.STOCK_SERVICE_TOPIC.realValue,
            objectMapper.writeValueAsString(message)
        )
    }

}