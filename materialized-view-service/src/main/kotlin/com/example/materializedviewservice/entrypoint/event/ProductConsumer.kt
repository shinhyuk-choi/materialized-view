package com.example.materializedviewservice.entrypoint.event


import com.example.materializedviewservice.domain.ProductDetailService
import com.example.materializedviewservice.entrypoint.event.dto.ProductUpsertDto
import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component
import java.util.concurrent.ConcurrentHashMap


@Component
class ProductConsumer(
    private val productDetailService: ProductDetailService
) {
    private val objectMapper = ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
    private val idHistoryMap: MutableMap<String, Int> = ConcurrentHashMap()

    @KafkaListener(topics = ["product-service-topic"], groupId = "product-consumer-group", concurrency = "1")
    @Throws(JsonProcessingException::class)
    fun listen(message: ConsumerRecord<String, String>) {
        val dto = objectMapper.readValue(message.value(), ProductUpsertDto::class.java)
        print(dto)
        productDetailService.upsertProduct(dto)
    }

    //private fun printPayloadIfFirstMessage(myMessage: MyMessage) {
    //    if (idHistoryMap.putIfAbsent(myMessage.id.toString(), 1) == null) {
    //        println("[Main Consumer(${Thread.currentThread().id})] Message arrived! - $myMessage")
    //    } else {
    //        println("[Main Consumer(${Thread.currentThread().id})] Duplicate! (${myMessage.id})")
    //    }
    //}
}