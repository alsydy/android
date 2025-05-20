package com.alsydy.accountbook.repository

import com.alsydy.accountbook.data.Account
import com.alsydy.accountbook.data.AccountDao
import kotlinx.coroutines.flow.Flow

class AccountRepository(private val accountDao: AccountDao) {
    fun getAllAccounts(): Flow<List<Account>> = accountDao.getAllAccounts()
    suspend fun insertAccount(account: Account) = accountDao.insertAccount(account)
    suspend fun deleteAccount(account: Account) = accountDao.deleteAccount(account)
}