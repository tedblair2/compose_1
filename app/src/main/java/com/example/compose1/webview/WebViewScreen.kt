package com.example.compose1.webview

import android.graphics.Bitmap
import android.util.Log
import android.webkit.WebView
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.web.*

@Composable
fun WebViewMainScreen() {
    var url by remember { mutableStateOf("https://search.brave.com/") }
    val state = rememberWebViewState(url = url)
    val navigator= rememberWebViewNavigator()
    var textFieldValue by remember(state.lastLoadedUrl) {
        mutableStateOf(state.lastLoadedUrl ?: " ")
    }
    
    Column {
        TopAppBar {
            IconButton(onClick = { navigator.navigateBack() }) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription =null)
            }
            IconButton(onClick = { navigator.navigateForward() }) {
                Icon(imageVector = Icons.Default.ArrowForward, contentDescription =null)
            }
            Text(text = "WebBrowser",
                fontSize = 20.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold)
            Row(horizontalArrangement = Arrangement.End, modifier = Modifier.weight(1f)) {
                IconButton(onClick = { navigator.reload() }) {
                    Icon(imageVector = Icons.Default.Refresh, contentDescription =null)
                }
                IconButton(onClick = { url = textFieldValue }) {
                    Icon(imageVector = Icons.Default.Check, contentDescription =null)
                }
            }
        }
        Row(modifier = Modifier.padding(all=12.dp)) {
            BasicTextField(
                value = textFieldValue,
                onValueChange ={ textFieldValue=it},
                maxLines = 1,
                modifier = Modifier.weight(9f))
            AnimatedVisibility(visible = state.errorsForCurrentRequest.isNotEmpty()) {
                Icon(
                    imageVector = Icons.Default.Warning,
                    contentDescription =null,
                    modifier = Modifier.weight(1f),
                    tint = Color.Red)
            }
        }
        val loadingState=state.loadingState
        if (loadingState is LoadingState.Loading){
            LinearProgressIndicator(
                progress = loadingState.progress,
                modifier = Modifier.fillMaxWidth())
        }
        val webClient= remember {
            object : AccompanistWebViewClient(){
                override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                    super.onPageStarted(view, url, favicon)
                    Log.d("WebView","page has started showing!")
                }
            }
        }
        WebView(
            state = state,
            modifier = Modifier.weight(1f),
            navigator=navigator,
            onCreated = {
                it.settings.javaScriptEnabled=true
            },
            client = webClient
        )
    }
}

@Preview(showBackground = true)
@Composable
fun WebViewPreview() {
    WebViewMainScreen()
}