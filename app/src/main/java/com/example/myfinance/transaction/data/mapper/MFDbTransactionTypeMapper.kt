package com.example.myfinance.transaction.data.mapper

import com.example.myfinance.core.Mapper
import com.example.myfinance.transaction.data.entity.TransactionTypeEntity
import com.example.myfinance.transaction.domain.model.TransactionType

class MFDbTransactionTypeMapper: Mapper<TransactionTypeEntity, TransactionType> {
    override fun from(item: TransactionType): TransactionTypeEntity {
        return TransactionTypeEntity(
            id = item.id,
            description = item.description
        )
    }

    override fun to(item: TransactionTypeEntity): TransactionType {
        return TransactionType(
            id = item.id,
            description = item.description
        )
    }
}