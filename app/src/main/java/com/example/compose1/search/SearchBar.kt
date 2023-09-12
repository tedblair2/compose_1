package com.example.compose1.search

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun MainSearchBar(appViewModel: AppViewModel,context: Context) {
    val searchWidgetState by appViewModel.searchWidgetState
    val searchTextState by appViewModel.searchTextState

    Scaffold(topBar = {
        MainAppBar(
            searchWidgetState = searchWidgetState,
            searchText = searchTextState,
            onTextChange = { appViewModel.setSearchTextState(it)},
            onCloseClicked = { appViewModel.setSearchWidgetState(SearchWidget.CLOSED) },
            onSearchClicked = { Toast.makeText(context, it , Toast.LENGTH_SHORT).show() },
            onSearchTriggered = {
                appViewModel.setSearchWidgetState(SearchWidget.OPENED)
            })
    }) {
        
    }
}

@Composable
fun MainAppBar(
    searchWidgetState:SearchWidget,
    searchText:String,
    onTextChange: (String) -> Unit,
    onCloseClicked: () -> Unit,
    onSearchClicked: (String) -> Unit,
    onSearchTriggered:() -> Unit
) {

    when(searchWidgetState){
        SearchWidget.CLOSED->{
            DefaultAppBar {
                onSearchTriggered()
            }
        }
        SearchWidget.OPENED->{
            SearchAppBar(
                text = searchText,
                onTextChange = {onTextChange(it)},
                onCloseClicked = { onCloseClicked() },
                onSearchClicked = { onSearchClicked(it) }
            )
        }
    }
}

@Composable
fun DefaultAppBar(onSearchClicked:()->Unit) {
    TopAppBar(
        title = { Text(text = "Home") },
        actions = {
            IconButton(onClick = { onSearchClicked() }) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search Icon",
                    tint = Color.White)
            }
        }
    )
}

@Composable
fun SearchAppBar(
    text:String,
    onTextChange:(String)->Unit,
    onCloseClicked:()->Unit,
    onSearchClicked: (String) -> Unit
) {
    val focusRequester = remember { FocusRequester() }
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp),
        elevation = AppBarDefaults.TopAppBarElevation,
        color = MaterialTheme.colors.primary ) {

        TextField(
            value = text,
            onValueChange = { onTextChange(it) },
            modifier = Modifier.fillMaxWidth()
                .focusRequester(focusRequester)
                .onGloballyPositioned { focusRequester.requestFocus() },
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.Transparent,
                cursorColor = Color.White.copy(alpha = ContentAlpha.medium)
            ),
            placeholder = { Text(
                text = "Search here...",
                modifier =Modifier.alpha(ContentAlpha.medium),
                color = Color.White)},
            textStyle = TextStyle(
                fontSize = MaterialTheme.typography.subtitle1.fontSize),
            singleLine = true,
            leadingIcon =  {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription ="search",
                    modifier = Modifier.alpha(ContentAlpha.medium),
                    tint = Color.White)
            },
            trailingIcon = {
                IconButton(onClick = {
                    if (text.isNotEmpty()) onTextChange("") else onCloseClicked()
                }) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription ="close",
                        tint = Color.White)
                }
            },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Search
            ), keyboardActions = KeyboardActions(
                onSearch = { onSearchClicked(text) }
            )
        )
    }
}

@Preview
@Composable
fun AppBarPreview(){
    DefaultAppBar {

    }
}

@Preview
@Composable
fun SearchTextPreview(){
    SearchAppBar(text = "search",
        onTextChange ={} ,
        onCloseClicked = { /*TODO*/ }, onSearchClicked = {})
}