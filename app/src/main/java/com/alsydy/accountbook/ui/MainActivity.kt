package com.alsydy.accountbook.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import com.alsydy.accountbook.data.AppDatabase
import com.alsydy.accountbook.repository.AccountRepository
import com.alsydy.accountbook.viewmodel.AccountViewModel
import com.alsydy.accountbook.viewmodel.AccountViewModelFactory
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val db = AppDatabase.getDatabase(this)
        val repo = AccountRepository(db.accountDao())
        val vmFactory = AccountViewModelFactory(repo)

        setContent {
            val vm: AccountViewModel = viewModel(factory = vmFactory)
            val accounts by vm.accounts.collectAsState()

            Scaffold(
                topBar = { TopAppBar(title = { Text("دفتر الحسابات") }) },
                floatingActionButton = {
                    FloatingActionButton(onClick = {
                        startActivity(Intent(this, AddAccountActivity::class.java))
                    }) {
                        Text("+")
                    }
                }
            ) { padding ->
                LazyColumn(
                    contentPadding = padding,
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(accounts.size) { i ->
                        val account = accounts[i]
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp)
                                .clickable {
                                    // فتح تفاصيل الحساب
                                    val intent = Intent(this@MainActivity, AccountDetailsActivity::class.java)
                                    intent.putExtra("accountId", account.id)
                                    startActivity(intent)
                                }
                        ) {
                            Row(
                                modifier = Modifier.padding(16.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = account.name,
                                    modifier = Modifier.weight(1f)
                                )
                                Text(
                                    text = account.balance.toString(),
                                    style = MaterialTheme.typography.titleLarge
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}