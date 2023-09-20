package com.example.compose1.canvas

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.compose1.R


@Composable
fun CarSeatParent(bookedList: List<Seat> = emptyList()) {
    val screenConfiguration= LocalConfiguration.current
    val screenWidth=screenConfiguration.screenWidthDp.dp-10.dp
    val seatSize=screenWidth/6
    val selectedSeats = remember { mutableStateListOf<Seat>() }
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(5.dp)
        .verticalScroll(rememberScrollState())) {
        for (row in 1..12){
            Row {
                for (column in 1..6){
                    if (column in 3..4 && row != 12){
                        Spacer(modifier = Modifier
                            .size(seatSize)
                            .padding(5.dp))
                    }else if (column==4){
                        Spacer(modifier = Modifier
                            .size(seatSize)
                            .padding(5.dp))
                    }else if (column==3){
                        val seatState=when(Seat("25")){
                            in bookedList->SeatState.BOOKED
                            in selectedSeats->SeatState.SELECTED
                            else->SeatState.UNSELECTED
                        }
                        CarSeatUi(carSeatSize = seatSize,
                            carSeat = "25", seatState = seatState,
                            modifier =Modifier.offset(x = seatSize/2),
                            onSelectSeat = {
                                if (selectedSeats.contains(it)){
                                    selectedSeats.remove(it)
                                }else{
                                    selectedSeats.add(it)
                                }
                            })
                    }else{
                        val seat=Seat(getSeatNo(row-1, column-1))
                        val seatState=when(seat){
                            in bookedList->SeatState.BOOKED
                            in selectedSeats->SeatState.SELECTED
                            else->SeatState.UNSELECTED
                        }
                        CarSeatUi(carSeatSize = seatSize,
                            seatState = seatState,
                            carSeat = getSeatNo(row-1, column-1),
                            onSelectSeat = {
                                if (selectedSeats.contains(it)){
                                    selectedSeats.remove(it)
                                }else{
                                    selectedSeats.add(it)
                                }
                            })
                    }
                }
            }
        }
    }
}

fun getSeatNo(row:Int,column:Int):String{
    var seatNo=-1
    when(column){
        0-> seatNo = (row*2)+1
        1-> seatNo = (row+1)*2
        2-> seatNo = 25
        4-> seatNo = (row*2)+1
        5-> seatNo = (row+1)*2
    }
    return if (column<=1){
        "A$seatNo"
    }else if (column>=4){
        "B$seatNo"
    }else{
        "25"
    }
}

@Composable
fun CarSeatUi(carSeat:String="A1",
              carSeatSize:Dp=100.dp,
              modifier: Modifier=Modifier,
              seatState: SeatState=SeatState.UNSELECTED,
              onSelectSeat:(seat:Seat)->Unit={}) {
    val seatColor by animateColorAsState(
        targetValue =when(seatState){
            SeatState.UNSELECTED-> Color.Gray
            SeatState.SELECTED-> Color.Red
            SeatState.BOOKED-> Color(0xFF8368E8)
        }, animationSpec = tween(300))
    Box(modifier = modifier
        .size(carSeatSize)
        .padding(5.dp)
        .clickable {
            if (seatState != SeatState.BOOKED){
                onSelectSeat(Seat(carSeat))
            }
        },
        contentAlignment = Alignment.Center){
        Image(painter = painterResource(id =R.drawable.baseline_event_seat_24),
            contentDescription ="seat",
            modifier = Modifier.fillMaxSize(),
            colorFilter = ColorFilter.tint(color = seatColor))
        Text(text = carSeat,
            fontSize = 12.sp,
            modifier = Modifier
                .offset(y =-(carSeatSize/6)))
    }
}

@Preview(showBackground = true)
@Composable
fun CustomPreview() {
    CarSeatParent()
}