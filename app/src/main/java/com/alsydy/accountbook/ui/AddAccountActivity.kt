package com.alsydy.accountbook.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.alsydy.accountbook.data.AppDatabase
import com.alsydy.accountbook.data.Account
import com.alsydy.accountbook.repository.AccountRepository
import com.alsydy.accountbook.viewmodel.AccountViewModel
import com.alsydy.accountbook.viewmodel.AccountViewModelFactory

class AddAccountActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val db = AppDatabase.getDatabase(this)
        val repo = AccountRepository(db.accountDao())
        val vmFactory = AccountViewModelFactory(repo)

        setContent {
            val vm: AccountViewModel = viewModel(factory = vmFactory)
            var name by remember { mutableStateOf("") }
            var balance by remember { mutableStateOf("") }
            var showDialog by remember { mutableStateOf(false) }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(32.dp),
                verticalArrangement = Arrangement.Center
            ) {
                Text("إضافة حساب جديد", style = MaterialTheme.typography.headlineSmall)
                Spacer(modifier = Modifier.height(16.dp))
                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text("اسم الحساب") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = balance,
                    onValueChange = { balance = it },
                    label = { Text("الرصيد الابتدائي") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = {
                        if (name.isNotBlank() && balance.isNotBlank()) {
                            vm.addAccount(Account(name = name, balance = balance.toDoubleOrNull() ?: 0.0))
                            showDialog = true
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("حفظ")
                }
            }

            if (showDialog) {
                AlertDialog(
                    onDismissRequest = { finish() },
                    confirmButton = {
                        Button(onClick = { finish() }) {
                            Text("حسناً")
                        }
                    },
                    title = { Text("تم الحفظ") },
                    text = { Text("تمت إضافة الحساب بنجاح!") }
                )
            }
        }
    }
}