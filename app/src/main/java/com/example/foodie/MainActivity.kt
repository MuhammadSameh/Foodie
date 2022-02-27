package com.example.foodie

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.example.foodie.models.Post
import com.example.foodie.models.User
import com.example.foodie.ui.theme.FoodieTheme
import com.example.foodie.ui.theme.Purple200
import com.example.foodie.ui.theme.Purple500
import com.example.foodie.ui.theme.Purple700
import com.example.utils.getTimelinePosts
import com.example.utils.getUser
import java.io.IOException
import java.io.InputStream

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContent {
            FoodieTheme {

                val user = getUser()
                val posts = getTimelinePosts()
                // A surface container using the 'background' color from the theme
                Scaffold(
                    topBar = { Header() },
                    bottomBar = { BottomNav() }
                ) { paddinValues ->
                    LazyColumn(modifier = Modifier.fillMaxSize(), contentPadding = paddinValues) {
                        item {
                            Spacer(modifier = Modifier.height(8.dp))
                            ShareExperience(user = user)
                            Divider(color = Color.LightGray, thickness = 4.dp)
                        }

                        items(posts.size) {
                            PostSection(post = posts[it])
                            Divider(color = Color.LightGray, thickness = 4.dp)
                        }

                    }
                }
            }
        }
    }
}


@Composable
fun Header() {
    TopAppBar(
        elevation = 4.dp,
        backgroundColor = Color.White,
        title = {
            Text(
                text = "LOGO",
                fontFamily = FontFamily.SansSerif,
                color = Purple500,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp
            )
        },
        actions = {
            IconButton(
                onClick = {},
                modifier = Modifier
                    .size(36.dp)
                    .clip(CircleShape)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_search),
                    contentDescription = "", tint = Purple500
                )
            }
            IconButton(
                onClick = {},
                modifier = Modifier
                    .size(36.dp)
                    .clip(CircleShape)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_notifications),
                    contentDescription = "", tint = Purple500
                )
            }
            IconButton(
                onClick = {},
                modifier = Modifier
                    .size(36.dp)
                    .clip(CircleShape)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_shopping_cart),
                    contentDescription = "", tint = Purple500
                )
            }
        }
    )
}

@OptIn(ExperimentalCoilApi::class)
@Composable
fun ShareExperience(user: User) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        val (image, text) = createRefs()
        val painter = rememberImagePainter(data = user.picturePath)
        Image(painter = painter, contentDescription = "",
            modifier = Modifier
                .constrainAs(image) {
                    linkTo(parent.top, parent.bottom, 16.dp, 16.dp)
                    start.linkTo(parent.start, margin = 16.dp)
                }
                .size(50.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop

        )

        Text(
            text = "Share your food experience",
            color = Color.Gray,
            modifier = Modifier
                .constrainAs(text) {
                    start.linkTo(image.end, margin = 16.dp)
                    end.linkTo(parent.end, margin = 16.dp)
                    top.linkTo(image.top)
                    bottom.linkTo(image.bottom)
                    width = Dimension.fillToConstraints
                }
                .border(width = 1.dp, color = Color.Gray, shape = CircleShape)
                .padding(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 8.dp),
            fontSize = 16.sp,
        )

    }
}

@Composable
fun BottomNav() {
    var selectedItem by remember { mutableStateOf(0) }
    val items = listOf(
        R.drawable.ic_home, R.drawable.ic_restaurant, R.drawable.ic_discount,
        R.drawable.ic_people, R.drawable.ic_person
    )
    BottomNavigation(backgroundColor = Color.White) {
        items.forEachIndexed { index, item ->
            BottomNavigationItem(
                selected = index == selectedItem,
                onClick = { selectedItem = index },
                icon = {
                    Icon(
                        painter = painterResource(id = item),
                        contentDescription = ""
                    )
                },
                modifier = Modifier
                    .background(brush = if (selectedItem == index) Brush.horizontalGradient(listOf(
                        Purple200, Purple500)) else Brush.horizontalGradient(listOf(Color.Transparent, Color.Transparent)), shape = CircleShape)

            )
        }
    }
}

@Composable
fun PostActions(post: Post) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        Button(
            onClick = { /*TODO*/ },
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
            elevation = ButtonDefaults.elevation(0.dp)
        ) {
            Text(text = post.likes, modifier = Modifier.padding(4.dp))
            Icon(
                painter = painterResource(id = R.drawable.ic_thumb_up),
                contentDescription = null,
                tint = Purple500
            )
        }
        Button(
            onClick = { /*TODO*/ },
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
            elevation = ButtonDefaults.elevation(0.dp)
        ) {
            Text(text = post.comments, modifier = Modifier.padding(4.dp))
            Icon(
                painter = painterResource(id = R.drawable.ic_comment),
                contentDescription = null,
                tint = Purple500
            )
        }
        Button(
            onClick = { /*TODO*/ },
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
            elevation = ButtonDefaults.elevation(0.dp)
        ) {
            Text(text = post.shares, modifier = Modifier.padding(4.dp))
            Icon(
                painter = painterResource(id = R.drawable.ic_share),
                contentDescription = null,
                tint = Purple500
            )
        }
    }
}

@OptIn(ExperimentalCoilApi::class)
@Composable
fun PostInformation(
    painter: Painter,
    isMoreEnabled: Boolean = true,
    post: Post,
    h1: String,
    h2: String
) {


    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            modifier = Modifier
                .padding(end = 8.dp)
                .size(50.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop,
            painter = painter,
            contentDescription = null,

            )
        Column {
            Row {
                Text(h1)
                Spacer(modifier = Modifier.width(5.dp))
                if (post !is Post.SharedPost) {
                    if (post.postedBy is User.Restaurant) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_restaurant),
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier.background(shape = CircleShape,
                                brush = Brush.horizontalGradient(
                                    listOf(Purple200, Purple700)
                                )
                            ).padding(3.dp).size(14.dp)
                        )
                    } else {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_verified),
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier.clip(CircleShape).background(
                                brush = Brush.horizontalGradient(
                                    listOf(Purple200, Purple500)
                                )
                            ).padding(3.dp).size(14.dp)
                        )
                    }
                }
                if (post is Post.SharedPost) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_arrow_forward),
                    contentDescription = null, tint = Purple500,
                )

                Text(text = post.postedTo.name)
            }

            }

            Text(text = h2, color = Color.LightGray)
        }





        if (isMoreEnabled) {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.TopEnd
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_more),
                    contentDescription = null,
                    modifier = Modifier
                        .size(60.dp)
                        .padding(8.dp)
                        .clip(CircleShape),


                    )
            }
        }


    }
}

@OptIn(ExperimentalCoilApi::class)
@Composable
fun NormalPostContent(post: Post) {
    val painter = rememberImagePainter(data = post.postImagePath, builder = {
        crossfade(true) //Crossfade animation between images
        placeholder(R.drawable.ic_loading) //Used while loading
        fallback(R.drawable.ic_empty) //Used if data is null
        error(R.drawable.ic_empty) //Used when loading returns with error
    })
    Column {
        Image(
            painter = painter, contentDescription = null, contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
                .height(300.dp)
        )
        if (post is Post.ReviewPost) {
            PostInformation(
                post = post,
                isMoreEnabled = false,
                h1 = post.mealReviewd,
                h2 = post.restaurantReviewed.name,
                painter = rememberImagePainter(data = post.restaurantReviewed.picturePath)
            )
        } else {
            PostInformation(
                post = post,
                isMoreEnabled = false,
                h1 = post.postedBy.name,
                h2 = "Cafe & Restaurant",
                painter = rememberImagePainter(post.postedBy.picturePath)
            )
        }

    }
}

@OptIn(ExperimentalCoilApi::class)
@Composable
fun PostSection(post: Post) {
    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.padding(8.dp)) {
        PostInformation(
            painter = rememberImagePainter(post.postedBy.picturePath),
            post = post,
            h1 = post.postedBy.name,
            h2 = "2 days ago"
        )
        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.TopStart)
        {
            Text(text = post.textContent)
        }
        Spacer(modifier = Modifier.height(10.dp))

        if (post is Post.SharedPost) {
            Card(shape = RoundedCornerShape(5.dp), elevation = 3.dp) {
                Column() {
                    PostInformation(
                        painter = rememberImagePainter(post.postedBy.picturePath),
                        post = post.originalPost,
                        h1 = post.originalPost.postedBy.name,
                        h2 = "2 days ago"
                    )
                    NormalPostContent(post = post.originalPost)
                }

            }
        } else {
            NormalPostContent(post = post)
        }

        PostActions(post = post)
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {

}