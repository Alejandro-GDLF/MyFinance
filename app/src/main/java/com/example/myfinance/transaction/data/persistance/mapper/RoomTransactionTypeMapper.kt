package com.example.myfinance.transaction.data.persistance.mapper

import com.example.myfinance.transaction.data.persistance.entity.TransactionTypeEntity
import com.example.myfinance.transaction.domain.model.TransactionType

object RoomTransactionTypeMapper {
    fun toPersistance(item: TransactionType): TransactionTypeEntity {
        return TransactionTypeEntity(
            id = item.id,
            description = item.description
        )
    }

    fun toDomain(item: TransactionTypeEntity): TransactionType {
        return TransactionType(
            id = item.id,
            description = item.description
        )
    }
}