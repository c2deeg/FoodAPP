package com.app.marier.Models.LikeUser

data class Data(
    val _id: String,
    val createdAt: String,
    val isLiked: Boolean,
    val isSuperLike: Boolean,
    val is_deleted: Boolean,
    val likeBy: String,
    val likeTo: String,
    val updatedAt: String
)