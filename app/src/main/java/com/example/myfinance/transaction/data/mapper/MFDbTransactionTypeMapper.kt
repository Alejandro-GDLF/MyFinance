package com.example.myfinance.transaction.data.mapper

import com.example.myfinance.core.Mapper
import com.example.myfinance.transaction.data.entity.TransactionTypeDbDto
import com.example.myfinance.transaction.domain.model.TransactionType

class MFDbTransactionTypeMapper: Mapper<TransactionTypeDbDto, TransactionType> {
    override fun from(item: TransactionType): TransactionTypeDbDto {
        return TransactionTypeDbDto(
            id = item.id,
            description = item.description
        )
    }

    override fun to(item: TransactionTypeDbDto): TransactionType {
        return TransactionType(
            id = item.id,
            description = item.description
        )
    }
}