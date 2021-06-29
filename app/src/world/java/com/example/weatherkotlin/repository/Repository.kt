package com.example.weatherkotlin.repository

import com.example.weatherkotlin.model.Weather

interface Repository {
    fun getWeatherFromServer(): Weather
    fun getWeatherFromLocalStorageWorld(): List<Weather>
}
