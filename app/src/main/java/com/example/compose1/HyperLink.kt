package com.example.compose1

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.ClickableText
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.sp

@Composable
fun HyperLinkText(){
    Column(modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {

        HyperText(link = "Psarips",
            fulltext ="Check out this site to download movies Psarips" , hyperlink ="https://psa.wf/" )
    }
}

@Composable
fun HyperText(link:String,fulltext:String,hyperlink:String){

    val annotatedString=buildAnnotatedString {
        append(fulltext)
        val startIndex=fulltext.indexOf(link)
        val endIndex=startIndex + link.length
        addStyle(
            style = SpanStyle(
                fontSize = 20.sp,
                color = Color.Black
            ),
            start = 0,
            end = fulltext.length
        )
        addStyle(
            style = SpanStyle(
                color = Color.Blue,
                textDecoration = TextDecoration.Underline,
                fontSize = 20.sp
            ),
            start = startIndex,
            end = endIndex
        )
        addStringAnnotation(
            tag = "URL",
            annotation = hyperlink,
            start = startIndex,
            end = endIndex
        )
    }
    val uriHandler= LocalUriHandler.current
    
    ClickableText(
        text =annotatedString , onClick ={
            annotatedString.getStringAnnotations("URL",it,it)
                .firstOrNull()?.let {stringAnnotation->
                    uriHandler.openUri(stringAnnotation.item)
                }
        })

}