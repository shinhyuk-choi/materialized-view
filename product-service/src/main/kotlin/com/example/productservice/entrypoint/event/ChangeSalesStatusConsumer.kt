//package com.example.productservice.entrypoint.event
//
//import com.example.productservice.application.usecase.ChangeSalesStatusUseCase
//import com.example.productservice.domain.dto.ChangeSalesStatusCommand
//import com.fasterxml.jackson.databind.DeserializationFeature
//import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
//import org.apache.kafka.clients.consumer.ConsumerRecord
//import org.apache.kafka.clients.consumer.ConsumerRecords
//import org.apache.kafka.clients.consumer.KafkaConsumer
//import org.springframework.beans.factory.annotation.Value
//import org.springframework.stereotype.Component
//import java.time.Duration
//import java.util.*
//
//@Component
//class ChangeSalesStatusConsumer(
//    @Value("\${kafka.clusters.bootstrapservers}") bootstrapServers: String,
//    @Value("\${logging.topic}") topic: String,
//    private val changeSalesStatusUseCase: ChangeSalesStatusUseCase,
//) {
//    private val consumer: KafkaConsumer<String, String>
//    private val objectMapper = jacksonObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
//
//
//    init {
//        val props = Properties()
//        props["bootstrap.servers"] = bootstrapServers
//        props["group.id"] = "my-group"
//        props["key.deserializer"] = "org.apache.kafka.common.serialization.StringDeserializer"
//        props["value.deserializer"] = "org.apache.kafka.common.serialization.StringDeserializer"
//        consumer = KafkaConsumer<String, String>(props)
//        consumer.subscribe(listOf(topic))
//
//        val consumerThread = Thread {
//            try {
//                while (true) {
//                    val records: ConsumerRecords<String, String> = consumer.poll(Duration.ofSeconds(1))
//                    for (record: ConsumerRecord<String, String> in records) {
//                        println("Received message: " + record.value())
//                        command = ChangeSalesStatusCommand(
//                            productId = record.value().productId,
//                            salesStatus = record.value().salesStatus,
//                        )
//                        changeSalesStatusUseCase.changeSalesStatus(command)
//                    }
//                }
//            } finally {
//                consumer.close()
//            }
//        }
//        consumerThread.start()
//    }
////}a