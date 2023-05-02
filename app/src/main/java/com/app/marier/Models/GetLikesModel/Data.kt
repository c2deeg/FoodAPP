package com.app.marier.Models.GetLikesModel

data class Data(
    val _id: String,
    val createdAt: String,
    val isLiked: Boolean,
    val is_deleted: Boolean,
    val likeBy: LikeBy,
    val likeTo: String,
    val updatedAt: String
)