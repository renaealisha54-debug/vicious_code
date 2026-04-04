package com.viciouscode.viciouscode

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import io.github.rosemoe.sora.widget.CodeEditor
import io.github.rosemoe.sora.widget.schemes.EditorColorScheme

class MainEditorActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ViciousCodeTheme {
                MainScreen()
            }
        }
        startService(Intent(this, FloatingOverlayService::class.java)) // start overlay
    }
}

@Composable
fun MainScreen() {
    var code by remember { mutableStateOf("// Drop your code here\nfun main() {\n    println(\"ViciousCode runs everything\")\n}") }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("ViciousCode Open Field Editor", style = MaterialTheme.typography.headlineMedium)

        AndroidView(
            factory = { ctx ->
                CodeEditor(ctx).apply {
                    setColorScheme(EditorColorScheme())
                    typefaceText = android.graphics.Typeface.MONOSPACE
                    // Real syntax support for all languages via TextMate
                    setText(code)
                }
            },
            modifier = Modifier.weight(1f).fillMaxWidth(),
            update = { editor ->
                editor.setText(code)
                editor.textChangedListener = { code = it.toString() }
            }
        )

        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Button(onClick = { /* call correctCode() */ }) { Text("Correct Code") }
            Button(onClick = { /* call convertCode() */ }) { Text("Convert Language") }
            Button(onClick = { /* call buildCompileRun() */ }) { Text("Build • Compile • Run") }
            Button(onClick = { /* call scanAndAddPermissions() */ }) { Text("Scan + Permissions") }
            Button(onClick = { /* call exportApk() */ }) { Text("Export APK") }
        }
    }
}
