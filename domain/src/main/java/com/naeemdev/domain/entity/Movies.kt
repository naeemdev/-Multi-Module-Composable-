package com.naeemdev.domain.entity


data class Movies(
    val results: List<NetworkMovie>,
    val currentPage: Int,
    val totalPages: Int,
)