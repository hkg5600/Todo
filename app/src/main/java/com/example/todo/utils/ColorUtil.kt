package com.example.todo.utils

import androidx.compose.ui.graphics.Color

fun Color.Companion.parsColor(color: String): Color {
    return Color(android.graphics.Color.parseColor(color))
}