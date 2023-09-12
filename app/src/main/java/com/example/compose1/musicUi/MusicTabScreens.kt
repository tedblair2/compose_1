package com.example.compose1.musicUi

sealed class MusicTabScreens(val title:String){
    object Songs:MusicTabScreens("SONGS")
    object Albums:MusicTabScreens("ALBUMS")
    object Playlist:MusicTabScreens("PLAYLISTS")
}
