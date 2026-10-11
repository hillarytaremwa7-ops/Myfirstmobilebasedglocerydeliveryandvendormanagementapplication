package com.example.myfirstmobilebasedglocerydeliveryandvendormanagementapplication.ui.components

import android.graphics.BitmapFactory
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.example.myfirstmobilebasedglocerydeliveryandvendormanagementapplication.util.ProductImageHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.net.HttpURLConnection
import java.net.URL

@Composable
fun ProductImageView(
    productName: String,
    imageUrl: String = "",
    category: String = "",
    modifier: Modifier = Modifier
) {
    val realUrl = remember(productName, category, imageUrl) {
        ProductImageHelper.getRealImageUrl(productName, category, imageUrl)
    }

    var loadedBitmap by remember(realUrl) { mutableStateOf<ImageBitmap?>(null) }
    var isLoading by remember(realUrl) { mutableStateOf(false) }

    LaunchedEffect(realUrl) {
        if (realUrl.startsWith("http://") || realUrl.startsWith("https://")) {
            isLoading = true
            withContext(Dispatchers.IO) {
                try {
                    val url = URL(realUrl)
                    val connection = url.openConnection() as HttpURLConnection
                    connection.connectTimeout = 6000
                    connection.readTimeout = 6000
                    connection.doInput = true
                    connection.connect()
                    val inputStream = connection.inputStream
                    val bitmap = BitmapFactory.decodeStream(inputStream)
                    inputStream.close()
                    if (bitmap != null) {
                        loadedBitmap = bitmap.asImageBitmap()
                    }
                } catch (e: Exception) {
                    loadedBitmap = null
                } finally {
                    isLoading = false
                }
            }
        }
    }

    Box(
        modifier = modifier
            .clip(RoundedCornerShape(12.dp))
            .background(Color(0xFFF1F8E9)),
        contentAlignment = Alignment.Center
    ) {
        when {
            loadedBitmap != null -> {
                Image(
                    bitmap = loadedBitmap!!,
                    contentDescription = productName,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            }
            isLoading -> {
                CircularProgressIndicator(
                    modifier = Modifier.size(20.dp),
                    color = MaterialTheme.colorScheme.primary,
                    strokeWidth = 2.dp
                )
            }
            else -> {
                ProductIllustration(productName)
            }
        }
    }
}

@Composable
private fun ProductIllustration(productName: String) {
    val nameLower = productName.lowercase()

    Canvas(modifier = Modifier.fillMaxSize().padding(12.dp)) {
        val w = size.width
        val h = size.height
        val cx = w / 2f
        val cy = h / 2f

        when {
            nameLower.contains("tomato") -> {
                drawCircle(color = Color(0xFFE53935), radius = minOf(w, h) * 0.38f, center = Offset(cx, cy + 4f))
                drawCircle(color = Color(0xFFFF8A80), radius = minOf(w, h) * 0.1f, center = Offset(cx - w * 0.15f, cy - h * 0.12f))
                val leafPath = Path().apply {
                    moveTo(cx, cy - h * 0.35f)
                    lineTo(cx - w * 0.12f, cy - h * 0.45f)
                    lineTo(cx - w * 0.04f, cy - h * 0.32f)
                    lineTo(cx + w * 0.12f, cy - h * 0.45f)
                    lineTo(cx + w * 0.04f, cy - h * 0.32f)
                    close()
                }
                drawPath(leafPath, color = Color(0xFF2E7D32))
            }

            nameLower.contains("onion") -> {
                drawCircle(color = Color(0xFF8E24AA), radius = minOf(w, h) * 0.35f, center = Offset(cx, cy + 4f))
                drawCircle(color = Color(0xFFBA68C8), radius = minOf(w, h) * 0.22f, center = Offset(cx, cy + 4f))
                drawCircle(color = Color(0xFFE1BEE7), radius = minOf(w, h) * 0.1f, center = Offset(cx, cy + 4f))
                val sproutPath = Path().apply {
                    moveTo(cx, cy - h * 0.32f)
                    lineTo(cx - w * 0.08f, cy - h * 0.48f)
                    lineTo(cx + w * 0.08f, cy - h * 0.48f)
                    close()
                }
                drawPath(sproutPath, color = Color(0xFF4CAF50))
            }

            nameLower.contains("matooke") || nameLower.contains("banana") -> {
                drawRoundRect(color = Color(0xFF2E7D32), topLeft = Offset(cx - w * 0.3f, cy - h * 0.2f), size = Size(w * 0.6f, h * 0.45f), cornerRadius = CornerRadius(20f, 20f))
                drawRoundRect(color = Color(0xFF388E3C), topLeft = Offset(cx - w * 0.22f, cy - h * 0.28f), size = Size(w * 0.44f, h * 0.45f), cornerRadius = CornerRadius(20f, 20f))
                drawRect(color = Color(0xFF1B5E20), topLeft = Offset(cx - w * 0.08f, cy - h * 0.42f), size = Size(w * 0.16f, h * 0.16f))
            }

            nameLower.contains("potato") -> {
                drawRoundRect(color = Color(0xFF8D6E63), topLeft = Offset(cx - w * 0.35f, cy - h * 0.25f), size = Size(w * 0.7f, h * 0.5f), cornerRadius = CornerRadius(40f, 40f))
                drawCircle(color = Color(0xFF5D4037), radius = 6f, center = Offset(cx - w * 0.15f, cy - h * 0.05f))
                drawCircle(color = Color(0xFF5D4037), radius = 5f, center = Offset(cx + w * 0.12f, cy + h * 0.08f))
                drawCircle(color = Color(0xFF5D4037), radius = 6f, center = Offset(cx, cy - h * 0.12f))
            }

            nameLower.contains("cabbage") -> {
                drawCircle(color = Color(0xFF4CAF50), radius = minOf(w, h) * 0.38f, center = Offset(cx, cy))
                drawCircle(color = Color(0xFF81C784), radius = minOf(w, h) * 0.28f, center = Offset(cx, cy))
                drawCircle(color = Color(0xFFC8E6C9), radius = minOf(w, h) * 0.16f, center = Offset(cx, cy))
            }

            nameLower.contains("sukuma") || nameLower.contains("greens") || nameLower.contains("vegetable") -> {
                val leafPath = Path().apply {
                    moveTo(cx, cy + h * 0.4f)
                    quadraticTo(cx - w * 0.4f, cy, cx - w * 0.2f, cy - h * 0.4f)
                    quadraticTo(cx, cy - h * 0.48f, cx + w * 0.2f, cy - h * 0.4f)
                    quadraticTo(cx + w * 0.4f, cy, cx, cy + h * 0.4f)
                    close()
                }
                drawPath(leafPath, color = Color(0xFF1B5E20))
                drawLine(color = Color(0xFF81C784), start = Offset(cx, cy + h * 0.38f), end = Offset(cx, cy - h * 0.42f), strokeWidth = 5f)
            }

            nameLower.contains("avocado") -> {
                drawRoundRect(color = Color(0xFF33691E), topLeft = Offset(cx - w * 0.28f, cy - h * 0.38f), size = Size(w * 0.56f, h * 0.76f), cornerRadius = CornerRadius(60f, 60f))
                drawRoundRect(color = Color(0xFFD4E157), topLeft = Offset(cx - w * 0.22f, cy - h * 0.32f), size = Size(w * 0.44f, h * 0.64f), cornerRadius = CornerRadius(50f, 50f))
                drawCircle(color = Color(0xFF5D4037), radius = minOf(w, h) * 0.14f, center = Offset(cx, cy + h * 0.08f))
            }

            nameLower.contains("mango") -> {
                drawRoundRect(color = Color(0xFFFB8C00), topLeft = Offset(cx - w * 0.3f, cy - h * 0.32f), size = Size(w * 0.6f, h * 0.64f), cornerRadius = CornerRadius(55f, 55f))
                drawCircle(color = Color(0xFFFFD54F), radius = minOf(w, h) * 0.15f, center = Offset(cx - w * 0.08f, cy - h * 0.08f))
                drawRect(color = Color(0xFF2E7D32), topLeft = Offset(cx - w * 0.04f, cy - h * 0.42f), size = Size(w * 0.08f, h * 0.1f))
            }

            nameLower.contains("bean") || nameLower.contains("pulse") -> {
                drawCircle(color = Color(0xFFFBC02D), radius = minOf(w, h) * 0.18f, center = Offset(cx - w * 0.12f, cy - h * 0.08f))
                drawCircle(color = Color(0xFFFBC02D), radius = minOf(w, h) * 0.18f, center = Offset(cx + w * 0.12f, cy + h * 0.08f))
                drawCircle(color = Color(0xFFF9A825), radius = minOf(w, h) * 0.16f, center = Offset(cx + w * 0.05f, cy - h * 0.12f))
            }

            nameLower.contains("rice") -> {
                drawCircle(color = Color(0xFFFFF8E1), radius = minOf(w, h) * 0.36f, center = Offset(cx, cy + h * 0.05f))
                drawRoundRect(color = Color(0xFFFFECB3), topLeft = Offset(cx - w * 0.28f, cy - h * 0.25f), size = Size(w * 0.56f, h * 0.5f), cornerRadius = CornerRadius(30f, 30f))
            }

            nameLower.contains("posho") || nameLower.contains("maize") || nameLower.contains("flour") -> {
                drawRoundRect(color = Color(0xFFECEFF1), topLeft = Offset(cx - w * 0.28f, cy - h * 0.35f), size = Size(w * 0.56f, h * 0.7f), cornerRadius = CornerRadius(16f, 16f))
                drawCircle(color = Color(0xFFFBC02D), radius = minOf(w, h) * 0.14f, center = Offset(cx, cy))
            }

            nameLower.contains("milk") || nameLower.contains("dairy") -> {
                drawRoundRect(color = Color(0xFF81D4FA), topLeft = Offset(cx - w * 0.24f, cy - h * 0.35f), size = Size(w * 0.48f, h * 0.7f), cornerRadius = CornerRadius(20f, 20f))
                drawRoundRect(color = Color(0xFFE0F7FA), topLeft = Offset(cx - w * 0.18f, cy - h * 0.28f), size = Size(w * 0.36f, h * 0.55f), cornerRadius = CornerRadius(16f, 16f))
            }

            nameLower.contains("egg") -> {
                drawCircle(color = Color(0xFFFFB74D), radius = minOf(w, h) * 0.18f, center = Offset(cx - w * 0.14f, cy))
                drawCircle(color = Color(0xFFFFB74D), radius = minOf(w, h) * 0.18f, center = Offset(cx + w * 0.14f, cy))
                drawCircle(color = Color(0xFFFFE0B2), radius = minOf(w, h) * 0.16f, center = Offset(cx, cy - h * 0.12f))
            }

            else -> {
                drawRoundRect(color = Color(0xFFC62828), topLeft = Offset(cx - w * 0.32f, cy - h * 0.28f), size = Size(w * 0.64f, h * 0.56f), cornerRadius = CornerRadius(24f, 24f))
                drawLine(color = Color(0xFFFFCDD2), start = Offset(cx - w * 0.2f, cy - h * 0.1f), end = Offset(cx + w * 0.2f, cy + h * 0.1f), strokeWidth = 8f)
            }
        }
    }
}
