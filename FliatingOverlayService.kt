package com.viciouscode.viciouscode

import android.app.Service
import android.content.Intent
import android.graphics.PixelFormat
import android.os.IBinder
import android.view.*
import android.widget.EditText
import android.widget.Toast
import com.viciouscode.viciouscode.R

class FloatingOverlayService : Service() {

    private lateinit var windowManager: WindowManager
    private lateinit var overlayView: View
    private var isMinimized = false

    override fun onBind(intent: Intent?): IBinder? = null

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        windowManager = getSystemService(WINDOW_SERVICE) as WindowManager

        overlayView = LayoutInflater.from(this).inflate(R.layout.overlay_notepad, null) // create simple layout with EditText
        val params = WindowManager.LayoutParams(
            WindowManager.LayoutParams.WRAP_CONTENT,
            WindowManager.LayoutParams.WRAP_CONTENT,
            WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
            WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
            PixelFormat.TRANSLUCENT
        ).apply {
            x = 100
            y = 100
            gravity = Gravity.TOP or Gravity.START
        }

        val editText = overlayView.findViewById<EditText>(R.id.overlay_edittext)
        editText.setText("// Paste code here - works over any app")

        // Drag to move
        var lastX = 0
        var lastY = 0
        overlayView.setOnTouchListener { _, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    lastX = event.rawX.toInt()
                    lastY = event.rawY.toInt()
                }
                MotionEvent.ACTION_MOVE -> {
                    val dx = event.rawX.toInt() - lastX
                    val dy = event.rawY.toInt() - lastY
                    params.x += dx
                    params.y += dy
                    windowManager.updateViewLayout(overlayView, params)
                    lastX = event.rawX.toInt()
                    lastY = event.rawY.toInt()
                }
            }
            true
        }

        // Minimize button
        overlayView.findViewById<View>(R.id.btn_minimize).setOnClickListener {
            isMinimized = !isMinimized
            params.width = if (isMinimized) 200 else WindowManager.LayoutParams.WRAP_CONTENT
            windowManager.updateViewLayout(overlayView, params)
            Toast.makeText(this, if (isMinimized) "Minimized" else "Restored", Toast.LENGTH_SHORT).show()
        }

        windowManager.addView(overlayView, params)
        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        if (::overlayView.isInitialized) windowManager.removeView(overlayView)
    }
}
