package com.app.marier.Models.GetRandomList

data class GetRandomUserExample(
    val `data`: List<Data>,
    val isSuccess: Boolean,
    val message: String,
    val statusCode: Int
)