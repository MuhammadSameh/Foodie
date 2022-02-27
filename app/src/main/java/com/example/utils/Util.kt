package com.example.utils

import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import com.example.foodie.models.Post
import com.example.foodie.models.User


var posts = mutableListOf<Post>()

val currentUser = User.Reviewer(
    picturePath = "https://media.istockphoto.com/photos/young-man-shopping-online-picture-id1305615921",
    name = "Mohamed Sameh"
)

val anotherUser = User.Reviewer(
    picturePath = "https://media.istockphoto.com/photos/profil-portrait-of-beautiful-sporty-woman-outdoors-picture-id1286187442?s=612x612",
    name = "Ryna Rahiel"
)

val chickenChister = User.Restaurant(picturePath = "https://visitwatertownsd.com/assets/caches/images/assets/uploads/gallery-images/_gallery/ChestersChicken_665x335-665x336.jpg",
name = "ChickenChister","https://www.chesterschicken.com/getmedia/478a9e87-042f-4ddd-999b-bdceb177f3ba/tenders.png?width=400&height=235&ext=.png")


val firstPost = Post.ReviewPost(currentUser,1645977402,"Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum",restaurantReviewed = chickenChister
, "Buy two chicken Burger Compo","200", "500", "1000")

val restaurantPost = Post.RestaurantPost(postedBy = chickenChister,timeStamp = 1645977402,
"Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum","https://www.chesterschicken.com/getmedia/478a9e87-042f-4ddd-999b-bdceb177f3ba/tenders.png?width=400&height=235&ext=.png",
likes = "300", comments = "200", shares = "500")

val originalPost = Post.ReviewPost(
    anotherUser,1645977402,"Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum",restaurantReviewed = chickenChister
    , "Buy two chicken Burger Compo","200", "500", "1000")

val sharedPost = Post.SharedPost(originalPost, chickenChister)





fun getTimelinePosts(): MutableList<Post>  {
    posts.add(firstPost)
    posts.add(sharedPost)
    posts.add(restaurantPost)

    return posts
}

fun getUser() = currentUser





