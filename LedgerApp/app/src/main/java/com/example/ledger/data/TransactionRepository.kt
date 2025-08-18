package com.example.ledger.data

class TransactionRepository(private val dao: TransactionDao) {
    val items = dao.getAll()

    suspend fun add(title: String, amount: Double, type: String) {
        dao.insert(TransactionEntity(title = title, amount = amount, type = type))
    }
    suspend fun delete(item: TransactionEntity) = dao.delete(item)
}