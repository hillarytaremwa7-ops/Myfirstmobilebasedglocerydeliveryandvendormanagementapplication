package com.example.myfirstmobilebasedglocerydeliveryandvendormanagementapplication.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun DeveloperFooter(
    modifier: Modifier = Modifier,
    color: Color = Color.Gray
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(end = 12.dp, bottom = 8.dp, top = 4.dp),
        contentAlignment = Alignment.BottomEnd
    ) {
        Text(
            text = "Developed by Taremwa Hillary",
            fontSize = 11.sp,
            fontStyle = FontStyle.Italic,
            color = color
        )
    }
}
