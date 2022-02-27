package com.example.foodie.models

sealed class User(
    val picturePath: String,
    val name: String,

) {
    class Reviewer(picturePath: String,
                   name: String): User(picturePath, name)

    class Restaurant(picturePath: String, name: String, val restaurantImagePath: String): User(picturePath,name)
}