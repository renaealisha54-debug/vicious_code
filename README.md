ViciousLayer
​ViciousLayer is a high-performance Android system overlay utility. It utilizes a background foreground service to inject a floating UI over any application on the device.
​Core Identity
​Environment: Android Native (Kotlin)
​Primary Tool: WindowManager API
​SDK Target: Android 14+ (API 34)
​Architecture
​MainActivity.kt: Manages the runtime permission bridge for SYSTEM_ALERT_WINDOW.
​ViciousOverlayService.kt: A sticky foreground service that maintains the floating window even when the main app is closed.
​overlay_layout.xml: Defines the visual look of the floating bubble.
​Installation
​Grant "Display over other apps" permission when prompted.
​The service will automatically start in the background.
​Drag the bubble to reposition it anywhere on your screen.
​Technical Notes
​The service is declared as special_use in the manifest to comply with Google Play's latest background execution policies for Android 14.
