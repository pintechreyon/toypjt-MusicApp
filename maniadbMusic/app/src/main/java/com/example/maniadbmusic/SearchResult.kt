package com.example.maniadbmusic

data class SearchResult(
    val artist: List<Artist>
)

data class Artist(
    val id: Int,
    val name: String
)
