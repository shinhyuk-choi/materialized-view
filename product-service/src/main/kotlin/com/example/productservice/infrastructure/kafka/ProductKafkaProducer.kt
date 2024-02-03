package com.example.productservice.infrastructure.kafka


import com.example.common.Topic
import com.example.productservice.domain.ProductEventProducer
import com.example.productservice.domain.ProductMessage
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component

@Component
class ProductKafkaProducer(
    private val kafkaTemplate: KafkaTemplate<String, Any>,
): ProductEventProducer {
    var objectMapper: ObjectMapper = ObjectMapper()

    override fun sendMessage(message: ProductMessage){
        kafkaTemplate.send(
            Topic.PRODUCT_SERVICE_TOPIC.realValue,
            objectMapper.writeValueAsString(message)
        )
    }

}