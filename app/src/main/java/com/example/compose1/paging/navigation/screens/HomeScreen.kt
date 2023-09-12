package com.example.compose1.paging.navigation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.compose1.R
import com.example.compose1.model.UnsplashImage
import com.example.compose1.model.Url
import com.example.compose1.model.User
import com.example.compose1.model.UserLinks
import com.example.compose1.paging.PagingViewModel
import com.example.compose1.paging.navigation.PagingScreen
import org.koin.androidx.compose.koinViewModel

@Composable
fun PagingHomeScreen(
    navHostController: NavHostController,
    viewModel: PagingViewModel= koinViewModel()) {
    val allImages=viewModel.allImages.collectAsLazyPagingItems()

    Scaffold(topBar = { TopAppBarContent {
        navHostController.navigate(PagingScreen.PagingSearch.route)
    }}) {
        PagingItemsColumn(unsplashImages = allImages)
    }
}

@Composable
fun TopAppBarContent(onSearchClicked:()->Unit) {
    TopAppBar(
        title = {
            Text(text = "Home",
                color = Color.White)
        },
        backgroundColor = MaterialTheme.colors.primary, 
        actions = {
            IconButton(onClick = { onSearchClicked() }) {
                Icon(imageVector = Icons.Default.Search,
                    contentDescription =null, tint = Color.White)
            }
        }
    )
}

@Composable
fun PagingItemsColumn(unsplashImages:LazyPagingItems<UnsplashImage>) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        contentPadding = PaddingValues(all = 12.dp)
    ){
        items(
            count = unsplashImages.itemCount,
            key=unsplashImages.itemKey { it.id }
        ){index ->
            unsplashImages[index]?.let { 
                PagingItem(unsplashImage = it)
            }
        }
    }
}

@Composable
fun PagingItem(unsplashImage: UnsplashImage){
    val context= LocalContext.current
    Box(modifier = Modifier
        .fillMaxWidth()
        .height(250.dp),
    contentAlignment = Alignment.BottomCenter) {
        AsyncImage(
            model = ImageRequest.Builder(context)
                .data(unsplashImage.urls.regular)
                .crossfade(true)
                .build(),
            contentDescription ="image_me",
            contentScale = ContentScale.Crop,
            placeholder = painterResource(id = R.drawable.not_available),
            error = painterResource(id = R.drawable.not_available),
            fallback = painterResource(id = R.drawable.not_available))
//        Image(painter = painterResource(id = R.drawable.b),
//            contentDescription = null, contentScale = ContentScale.Crop)
        Surface(modifier = Modifier
            .height(40.dp)
            .fillMaxWidth()
            .alpha(ContentAlpha.medium),
            color = Color.DarkGray) {}
        Row(modifier = Modifier
            .fillMaxWidth()
            .height(40.dp)
            .padding(horizontal = 6.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween){
            Text(buildAnnotatedString {
                append("Photo by ")
                withStyle(SpanStyle(fontWeight = FontWeight.Bold)){
                    append(unsplashImage.user.username)
                }
                append(" on UnSplash")
            }, color = Color.White,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                fontSize = 17.sp,
                modifier = Modifier.fillMaxWidth(0.8f))
            LikeCounter(likes = unsplashImage.likes.toString())
        }
    }
}

@Composable
fun LikeCounter(likes:String){
    Row(modifier = Modifier.fillMaxSize(),
        verticalAlignment =Alignment.CenterVertically,
        horizontalArrangement = Arrangement.End) {
        Icon(painter = painterResource(id = R.drawable.baseline_favorite_24),
            contentDescription = null, tint = Color.Red)
        Divider(modifier = Modifier.width(6.dp))
        Text(text =likes,
            color = Color.White,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1)
    }
}

@Preview
@Composable
fun PagingHomePreview() {
    PagingItem(unsplashImage = UnsplashImage(
        id = "me",
        urls = Url("image_link"),
        likes = 100,
        user = User("Ted Omino", links = UserLinks("link to user"))
    ))

}