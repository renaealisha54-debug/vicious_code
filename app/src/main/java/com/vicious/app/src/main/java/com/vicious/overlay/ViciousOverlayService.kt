package com.vicious.overlay

import android.app.Service
import android.content.Intent
import android.graphics.PixelFormat
import android.os.IBinder
import android.view.*

class ViciousOverlayService : Service() {
    private lateinit var windowManager: WindowManager
    private lateinit var floatingView: View

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        windowManager = getSystemService(WINDOW_SERVICE) as WindowManager
        floatingView = LayoutInflater.from(this).inflate(R.layout.overlay_layout, null)

        val params = WindowManager.LayoutParams(
            WindowManager.LayoutParams.WRAP_CONTENT,
            WindowManager.LayoutParams.WRAP_CONTENT,
            WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
            WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
            PixelFormat.TRANSLUCENT
        )

        // Basic "Movable" logic
        floatingView.setOnTouchListener { view, event ->
            if (event.action == MotionEvent.ACTION_MOVE) {
                params.x = event.rawX.toInt() - (view.width / 2)
                params.y = event.rawY.toInt() - (view.height / 2)
                windowManager.updateViewLayout(floatingView, params)
            }
            true
        }

        windowManager.addView(floatingView, params)
        return START_STICKY
    }

    override fun onBind(intent: Intent?): IBinder? = null
}
