package com.gabryel.mercadolivro.events

import com.gabryel.mercadolivro.model.PurchaseModel
import org.springframework.context.ApplicationEvent

class PurchaseEvent (source: Any, val purchaseModel: PurchaseModel) : ApplicationEvent(source) {
}