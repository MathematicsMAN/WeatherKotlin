package com.example.weatherkotlin.repository

import com.example.weatherkotlin.model.WeatherDTO

interface DetailsRepository {
    fun getWeatherDetailsFromServer(
//        requestLink: String,
        lat: Double,
        lon: Double,
        callback: retrofit2.Callback<WeatherDTO>
    )
}