package com.example.common

import org.apache.kafka.clients.producer.ProducerConfig
import org.springframework.boot.autoconfigure.kafka.KafkaProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.kafka.annotation.EnableKafka
import org.springframework.kafka.core.DefaultKafkaProducerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.core.ProducerFactory

@Configuration
@EnableKafka
class KafkaConfig {

    @Bean
    @Primary
    fun kafkaProperties(): KafkaProperties {
        return KafkaProperties()
    }

    @Bean
    fun producerFactory(
        kafkaProperties: KafkaProperties
    ): ProducerFactory<String, Any> {
        val props = HashMap<String, Any>()
        props[ProducerConfig.BOOTSTRAP_SERVERS_CONFIG] = kafkaProperties().bootstrapServers
        props[ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG] = kafkaProperties().producer.keySerializer
        props[ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG] = kafkaProperties().producer.valueSerializer
        props[ProducerConfig.ACKS_CONFIG] = kafkaProperties().producer.acks
        props[ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG] = "true"
        return DefaultKafkaProducerFactory(props)
    }

    @Bean
    @Primary
    fun KafkaTemplate(
        kafkaProperties: KafkaProperties
    ): KafkaTemplate<String, Any> {
        return KafkaTemplate(producerFactory(kafkaProperties))
    }
}