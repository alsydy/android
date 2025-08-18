package com.example.ledger.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.ledger.data.TransactionEntity
import com.example.ledger.data.TransactionRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class TransactionViewModel(private val repo: TransactionRepository) : ViewModel() {

    val items = repo.items.stateIn(viewModelScope, SharingStarted.Eagerly, emptyList())

    val balance = items.map { list ->
        list.sumOf { if (it.type == "IN") it.amount else -it.amount }
    }.stateIn(viewModelScope, SharingStarted.Eagerly, 0.0)

    fun add(title: String, amount: Double, type: String) {
        viewModelScope.launch {
            repo.add(title, amount, type)
        }
    }

    fun delete(item: TransactionEntity) {
        viewModelScope.launch {
            repo.delete(item)
        }
    }
}

@Suppress("UNCHECKED_CAST")
class VMFactory(private val repo: TransactionRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return TransactionViewModel(repo) as T
    }
}