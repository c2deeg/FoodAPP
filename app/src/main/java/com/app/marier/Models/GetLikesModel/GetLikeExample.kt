package com.app.marier.Models.GetLikesModel

data class GetLikeExample(
    val `data`: List<Data>,
    val isSuccess: Boolean,
    val message: String,
    val statusCode: Int
)