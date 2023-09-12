package com.example.compose1.musicUi

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.compose1.R

@Composable
fun MusicHome() {
    
}

@Composable
fun MusicSongs() {
    LazyColumn(modifier = Modifier.fillMaxSize().padding(top = 5.dp),
        verticalArrangement = Arrangement.spacedBy(5.dp)){
        items(20){
            MusicSong()
        }
    }
}

@Composable
fun MusicSong() {
    val musicTxtFontFamily= FontFamily(
        Font(R.font.anektelugu_bold, FontWeight.Bold),
        Font(R.font.anektelugu_medium,FontWeight.Medium),
        Font(R.font.anektelugu_medium,FontWeight.SemiBold),
        Font(R.font.anektelugu_regular, FontWeight.Normal)
    )
    Row(modifier = Modifier.fillMaxWidth().clickable {  },
        verticalAlignment = Alignment.CenterVertically) {
        Box(modifier = Modifier.size(50.dp)){
            Image(painter = painterResource(id = R.drawable.p32), contentDescription =null)
        }
        Column(modifier = Modifier.weight(1f).padding(start = 8.dp),
            verticalArrangement =Arrangement.spacedBy(2.dp)) {
            Text(text = "Song Name",
                fontSize = 18.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                fontFamily = musicTxtFontFamily,
                fontWeight = FontWeight.SemiBold,
                style = TextStyle(platformStyle = PlatformTextStyle(includeFontPadding = false))
            )
            Text(text = "3:21", fontSize = 15.sp,
                fontFamily = musicTxtFontFamily,
                fontWeight = FontWeight.Normal,
                style = TextStyle(platformStyle = PlatformTextStyle(includeFontPadding = false))
            )
        }
        IconButton(onClick = { /*TODO*/ }) {
            Icon(imageVector = Icons.Default.MoreVert, contentDescription =null )
        }
    }
}

@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun MusicPreview() {
    MusicSongs()
}