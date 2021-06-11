package com.example.weatherkotlin.repository

import com.example.weatherkotlin.model.Weather
import com.example.weatherkotlin.model.getRussianCities
import com.example.weatherkotlin.model.getWorldCities

class RepositoryImpl : Repository {
    override fun getWeatherFromServer() = Weather()

    override fun getWeatherFromLocalStorageRus() = getRussianCities()

    override fun getWeatherFromLocalStorageWorld() = getWorldCities()
}
