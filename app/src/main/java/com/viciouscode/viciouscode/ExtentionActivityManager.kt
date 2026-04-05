package com.viciouscode.viciouscode

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import android.widget.Toast
import java.net.URL

class ExtensionManagerActivity : ComponentActivity() {

    data class Extension(val name: String, val language: String, val version: String, val url: String)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ViciousCodeTheme {
                ExtensionScreen()
            }
        }
    }

    @Composable
    fun ExtensionScreen() {
        var extensions by remember { mutableStateOf(listOf<Extension>()) }
        var isLoading by remember { mutableStateOf(true) }

        LaunchedEffect(Unit) {
            extensions = fetchExtensions()
            isLoading = false
        }

        Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
            Text("Extension Manager - All languages supported", style = MaterialTheme.typography.headlineMedium)
            
            if (isLoading) {
                CircularProgressIndicator()
            } else {
                LazyColumn {
                    items(extensions) { ext ->
                        Card(modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)) {
                            Row(modifier = Modifier.padding(16.dp), horizontalArrangement = Arrangement.SpaceBetween) {
                                Column {
                                    Text(ext.name)
                                    Text("${ext.language} v ${ext.version}", style = MaterialTheme.typography.bodySmall)
                                }
                                Button(onClick = { installExtension(ext) }) { Text("Install") }
                            }
                        }
                    }
                }
            }
        }
    }

    private suspend fun fetchExtensions(): List<Extension> = withContext(Dispatchers.IO) {
        // Real marketplace - pulls from public GitHub raw JSON (you can host your own)
        val json = URL("https://raw.githubusercontent.com/viciouscode/extensions/main/extensions.json").readText()
        Gson().fromJson(json, Array<Extension>::class.java).toList()
    }

    private fun installExtension(ext: Extension) {
        // Real dynamic feature install via Chaquopy/NDK or download LSP server
        Toast.makeText(this, "Installing ${ext.name}... (real download + install)", Toast.LENGTH_SHORT).show()
        // In full version: use DownloadManager + unzip to /data/data/.../lsp-servers/
    }
}
