package com.example.weatherkotlin.repository

import com.example.weatherkotlin.model.Weather

interface LocalRepository {
    fun getAllHistory(): List<Weather>
    fun saveEntity(weather: Weather)
}