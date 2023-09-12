package com.example.compose1

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Phone
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun TextFieldExample(){
    var text by remember {
        mutableStateOf("")
    }
    Column(modifier = Modifier.fillMaxSize(),
    horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.Center) {
        OutlinedTextField(value =text ,
            onValueChange ={ text=it},
        label = { Text(text = "Phone")},
        singleLine = true,
        leadingIcon = {
            Icon(imageVector = Icons.Filled.Phone, contentDescription ="phone")
        },
        trailingIcon = {
            IconButton(onClick = {text = ""}) {
                Icon(imageVector = Icons.Filled.Clear, contentDescription ="clear")
            }
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Phone,
            imeAction = ImeAction.Go
        ),
        keyboardActions = KeyboardActions(
            onGo = {
                text=""
            }
        )
        )

    }
}

@Preview
@Composable
fun TextFieldPreview(){
    TextFieldExample()
}