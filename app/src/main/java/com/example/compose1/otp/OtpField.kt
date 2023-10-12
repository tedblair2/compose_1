package com.example.compose1.otp

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun OtpFieldUi(onOtpFilled:(String)->Unit={}) {
    var otpCode by rememberSaveable { mutableStateOf("") }
    val isOtpFilled by remember { derivedStateOf { otpCode.length==6 } }

    BasicTextField(value = otpCode,
        onValueChange = {
            if (it.length<=6){
                otpCode=it
            }
            if (isOtpFilled){
                onOtpFilled(otpCode)
            }
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword)
    ) {
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            repeat(6){index->
                val color= when (index) {
                    otpCode.length -> Color.Blue
                    else -> Color.Black
                }
                val number=when{
                    index >= otpCode.length->""
                    else->otpCode[index].toString()
                }
                Box(modifier = Modifier
                    .size(42.dp)
                    .border(width = 2.dp, color = color, shape = RoundedCornerShape(6.dp)),
                    contentAlignment = Alignment.Center ){
                    Text(text = number, fontSize = 20.sp)
                }
            }
        }
    }
}