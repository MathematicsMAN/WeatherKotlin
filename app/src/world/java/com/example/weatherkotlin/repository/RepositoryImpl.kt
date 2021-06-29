package com.example.weatherkotlin.repository

import com.example.weatherkotlin.model.Weather
import com.example.weatherkotlin.model.getWorldCities

class RepositoryImpl : Repository {
    override fun getWeatherFromServer() = Weather()

    override fun getWeatherFromLocalStorageWorld() = getWorldCities()
}
