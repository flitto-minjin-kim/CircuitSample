package com.minjin.sample.model

import androidx.compose.runtime.Immutable

@Immutable
data class Email(
    val id: String,
    val subject: String,
    val body: String,
    val sender: String,
    val timestamp: String,
    val recipients: List<String>,
)