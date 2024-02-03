package com.example.productservice.domain

import com.example.productservice.domain.dto.ChangeSalesStatusCommand
import com.example.productservice.domain.dto.RegisterProductCommand
import com.example.productservice.domain.entity.Product
import com.example.productservice.domain.entity.ProductOption
import com.example.productservice.domain.entity.ProductOptionGroup
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class ProductService(
    private val productReader: ProductReader,
    private val productStore: ProductStore,
    private val productEventProducer: ProductEventProducer,
) {

    @Transactional
    fun changeSalesStatus(
        command: ChangeSalesStatusCommand
    ) {
        val productIdToCommandMap = command.products.associateBy { it.id }
        val productOptionIdToCommandMap = command.products.flatMap { it.productOptions }.associateBy { it.id }

        val products = productReader.findAllById(ids = productIdToCommandMap.keys.toList())

        products.forEach { product ->
            val productCommand = productIdToCommandMap[product.id]!!
            product.changeSalesStatus(
                salesStatus = productCommand.salesStatus,
            )
            val options = product.optionGroups.flatMap { it.options }
            options.forEach { option ->
                val optionCommand = productOptionIdToCommandMap[option.id]!!
                option.changeSalesStatus(
                    salesStatus = optionCommand.salesStatus,
                )
            }
        }
        productStore.saveAll(products)

    }

    @Transactional
    fun register(command: RegisterProductCommand) {
        val product = Product.generate(
            name = command.name,
            price = command.price,
        )
        for (group in command.productOptionGroups) {
            val optionGroup = ProductOptionGroup.generate(
                name = group.name,
                ordering = group.ordering,
                product = product,
            )
            product.optionGroups.add(optionGroup)
            for (option in group.productOptions) {
                optionGroup.options.add(
                    ProductOption.generate(
                        name = option.name,
                        price = option.price,
                        ordering = option.ordering,
                        productOptionGroup = optionGroup,
                    )
                )
            }
        }
        productStore.saveAll(listOf(product))
        productEventProducer.sendMessage(ProductMessage.generate(product))
    }


}