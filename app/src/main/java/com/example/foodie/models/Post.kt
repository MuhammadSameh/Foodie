package com.example.foodie.models

sealed class Post(
    val postedBy: User,
    val timeStamp: Long,
    val textContent: String,
    val postImagePath: String,
    val likes: String,
    val comments: String,
    val shares: String
) {
    class RestaurantPost(
        postedBy: User,
        timeStamp: Long,
        textContent: String,
        postImagePath: String,
        likes: String,
        comments: String,
        shares: String
    ) : Post(
        postedBy,
        timeStamp,
        textContent,
        postImagePath,
        likes,
        comments,
        shares
    )

    class SharedPost(val originalPost: ReviewPost, val postedTo: User) :
        Post(
            originalPost.postedBy,
            originalPost.timeStamp,
            originalPost.textContent,
            originalPost.restaurantReviewed.restaurantImagePath,
            originalPost.likes,
            originalPost.comments,
            originalPost.shares
        )

    class ReviewPost(
        postedBy: User,
        timeStamp: Long,
        review: String,
        val restaurantReviewed: User.Restaurant,
        val mealReviewd: String,
        likes: String,
        comments: String,
        shares: String
    ) : Post(
        postedBy,
        timeStamp,
        review,
        restaurantReviewed.restaurantImagePath,
        likes,
        comments,
        shares
    )


}
