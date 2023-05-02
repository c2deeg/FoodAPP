package com.app.marier.Models.GetRandomList

data class Data(
    val _id: String,
    val avatar: String,
    val distance: Int,
    val dob: String,
    val gallery: List<Gallery>,
    val location: Location,
    val name: String,
    val sex: String
)