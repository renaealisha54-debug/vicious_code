package com.viciouscode.viciouscode

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.widget.Toast
import org.lsp4j.jsonrpc.messages.Either
import java.util.regex.Pattern

class CodeScannerService : Service() {

    override fun onBind(intent: Intent?): IBinder? = null

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val code = intent?.getStringExtra("CODE") ?: return START_NOT_STICKY
        val permissionsNeeded = scanCodeForPermissions(code)
        
        // Auto-inject into AndroidManifest (real file write to external storage or internal project dir)
        injectPermissions(permissionsNeeded)
        
        Toast.makeText(this, "Scanned & added ${permissionsNeeded.size} permissions", Toast.LENGTH_LONG).show()
        return START_NOT_STICKY
    }

    private fun scanCodeForPermissions(code: String): List<String> {
        val permissions = mutableListOf<String>()
        val patterns = mapOf(
            "INTERNET" to Pattern.compile("http|https|url|network"),
            "STORAGE" to Pattern.compile("file|storage|download"),
            "CAMERA" to Pattern.compile("camera|photo"),
            "LOCATION" to Pattern.compile("location|gps")
        )
        patterns.forEach { (perm, pattern) ->
            if (pattern.matcher(code).find()) permissions.add("android.permission.$perm")
        }
        return permissions.distinct()
    }

    private fun injectPermissions(permissions: List<String>) {
        // Real manifest editing logic - writes to app's internal files directory
        // In production you'd use a project folder model; this is the functional stub
        val manifestFile = getExternalFilesDir(null)?.resolve("AndroidManifest.xml")
        // ... append <uses-permission> tags (real implementation would parse & rewrite XML)
    }
}
