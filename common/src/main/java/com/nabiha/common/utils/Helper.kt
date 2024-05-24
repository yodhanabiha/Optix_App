package com.nabiha.common.utils

import java.text.NumberFormat
import java.util.Locale

fun formatPrice(price: Int): String {
    val formatter = NumberFormat.getNumberInstance(Locale("in", "ID"))
    return formatter.format(price)
}