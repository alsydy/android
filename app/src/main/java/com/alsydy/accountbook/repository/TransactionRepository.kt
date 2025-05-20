package com.alsydy.accountbook.repository

import com.alsydy.accountbook.data.Transaction
import com.alsydy.accountbook.data.TransactionDao
import kotlinx.coroutines.flow.Flow

class TransactionRepository(private val transactionDao: TransactionDao) {
    fun getTransactionsForAccount(accountId: Int): Flow<List<Transaction>> =
        transactionDao.getTransactionsForAccount(accountId)

    suspend fun insertTransaction(transaction: Transaction) =
        transactionDao.insertTransaction(transaction)

    suspend fun deleteTransaction(transaction: Transaction) =
        transactionDao.deleteTransaction(transaction)
}