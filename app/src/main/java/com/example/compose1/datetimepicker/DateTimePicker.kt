package com.example.compose1.datetimepicker

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.datetime.date.datepicker
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Composable
fun DatePickerDialogCompose() {
    var pickedDate by remember { mutableStateOf(LocalDate.now()) }
    val formattedDate by remember {
        derivedStateOf { DateTimeFormatter.ofPattern("MMM dd yyyy").format(pickedDate) }
    }
    val datePickerState= rememberMaterialDialogState()
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = { datePickerState.show() }) {
            Text(text = "Select Date")
        }
        Text(text = formattedDate, modifier = Modifier.padding(10.dp), fontSize = 20.sp)
        
        MaterialDialog(
            dialogState = datePickerState,
            buttons ={
                positiveButton(text = "Ok")
                negativeButton(text = "Cancel"){
                    pickedDate= LocalDate.now()
                }
            } ) {
            datepicker(
                initialDate = pickedDate,
                title = "Pick a Date",
                allowedDateValidator = { isValidDate(it) }){
                pickedDate=it
            }
        }
    }
}

fun isValidDate(date: LocalDate):Boolean{
    val now=LocalDate.now()
    val today=now.minusDays(1L)
    val thirtyDaysFromNow=now.plusDays(31L)
    return date.isAfter(today) && date.isBefore(thirtyDaysFromNow)
}