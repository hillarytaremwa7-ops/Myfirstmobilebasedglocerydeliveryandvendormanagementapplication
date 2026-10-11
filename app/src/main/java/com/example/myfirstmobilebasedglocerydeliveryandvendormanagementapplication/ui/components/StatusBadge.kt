package com.example.myfirstmobilebasedglocerydeliveryandvendormanagementapplication.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun StatusBadge(
    text: String,
    modifier: Modifier = Modifier
) {
    val upper = text.uppercase()
    val (bgColor, textColor) = when {
        upper.contains("PENDING") || upper.contains("UNASSIGNED") -> Color(0xFFFFF3E0) to Color(0xFFE65100)
        upper.contains("COMPLETED") || upper.contains("DELIVERED") || upper.contains("APPROVED") || upper.contains("ONLINE") -> Color(0xFFE8F5E9) to Color(0xFF2E7D32)
        upper.contains("FAILED") || upper.contains("CANCELLED") || upper.contains("REJECTED") || upper.contains("SUSPENDED") -> Color(0xFFFFEBEE) to Color(0xFFC62828)
        else -> Color(0xFFE3F2FD) to Color(0xFF1565C0)
    }

    Text(
        text = text,
        color = textColor,
        fontSize = 11.sp,
        fontWeight = FontWeight.Bold,
        modifier = modifier
            .background(bgColor, RoundedCornerShape(12.dp))
            .padding(horizontal = 8.dp, vertical = 3.dp)
    )
}
