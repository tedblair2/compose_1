package com.example.compose1

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.compose1.model.Person
import com.example.compose1.ui.theme.Shapes

@Composable
fun CustomPersonUi(person: Person){
    Column(modifier = Modifier
        .fillMaxWidth()
        .background(color = Color.LightGray, shape = Shapes.medium)
        .padding(10.dp),
    verticalArrangement = Arrangement.Center) {
        TextSpan(preText = "Name", mainTxt =person.name)
        TextSpan(preText = "Age", mainTxt =person.age.toString())
        TextSpan(preText = "Address", mainTxt =person.address)
    }
}

@Composable
fun TextSpan(preText:String,mainTxt:String){
    Text(buildAnnotatedString {
        withStyle(style = SpanStyle(
            fontSize = 14.sp
        )){
            append("$preText: ")
        }
        withStyle(style = SpanStyle(
            fontSize = 20.sp
        )
        ){
            append(mainTxt)
        }
    }, textAlign = TextAlign.Center)
}

@Composable
fun LazyColumUi(){
    val persons=AppRepository().getUsers()
    Column(modifier = Modifier.fillMaxSize()) {
        LazyColumn(contentPadding = PaddingValues(10.dp,5.dp),
        verticalArrangement = Arrangement.spacedBy(5.dp)){
            items(items = persons){person->
                CustomPersonUi(person = person)
            }
        }
    }
}


@Preview
@Composable
fun LazyColumnPreview(){
    LazyColumUi()
}