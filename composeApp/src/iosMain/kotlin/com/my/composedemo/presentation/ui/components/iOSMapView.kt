package com.my.composedemo.platform.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.interop.UIKitView
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.cValue
import platform.WebKit.WKWebView
import platform.WebKit.WKWebViewConfiguration

@OptIn(ExperimentalForeignApi::class)
@Composable
actual fun MapView(
    modifier: Modifier,
    onMapClick: (() -> Unit)?
) {
    UIKitView(
        factory = {
            val webView = WKWebView(frame = cValue { platform.CoreGraphics.CGRectZero }, configuration = WKWebViewConfiguration())
            
            // Google Maps Embed APIを使用
            val apiKey = platform.Foundation.NSBundle.mainBundle.objectForInfoDictionaryKey("GMSApiKey") as? String
            val htmlContent = """
                <!DOCTYPE html>
                <html>
                <head>
                    <meta name="viewport" content="width=device-width, initial-scale=1.0">
                    <style>
                        body, html { margin: 0; padding: 0; height: 100%; }
                        #map { height: 100%; width: 100%; }
                    </style>
                </head>
                <body>
                    <div id="map"></div>
                    <script>
                        function initMap() {
                            const tokyo = { lat: 35.6762, lng: 139.6503 };
                            const map = new google.maps.Map(document.getElementById("map"), {
                                zoom: 10,
                                center: tokyo,
                            });
                        }
                    </script>
                    <script async defer
                        src="https://maps.googleapis.com/maps/api/js?key=$apiKey&callback=initMap">
                    </script>
                </body>
                </html>
            """.trimIndent()
            
            webView.loadHTMLString(htmlContent, baseURL = null)
            webView
        },
        modifier = modifier.fillMaxSize(),
        update = { view ->
            // Update logic if needed
        }
    )
}

