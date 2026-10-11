package com.example.myfirstmobilebasedglocerydeliveryandvendormanagementapplication.ui.components

import java.text.NumberFormat
import java.util.Locale

fun Double.formatUgx(): String {
    val formatter = NumberFormat.getNumberInstance(Locale.US)
    return "UGX ${formatter.format(this.toLong())}"
}
