package com.example.weatherkotlin.utils

import com.example.weatherkotlin.model.*
import com.example.weatherkotlin.room.HistoryEntity

fun convertDtoToModel(weatherDTO: WeatherDTO): List<Weather> {
    val fact: FactDTO = weatherDTO.fact!!
    return listOf(Weather(getDefaultCity(), fact.temp!!, fact.feels_like!!, fact.condition!!, fact.icon!!))
}

fun convertHistoryEntityToWeather(entryList: List<HistoryEntity>): List<Weather> {
    return entryList.map {
        Weather(
            City(it.city, 0.0, 0.0),
            it.temperature,
            0,
            it.condition)
    }
}

fun convertWeatherToEntity(weather: Weather): HistoryEntity {
    return HistoryEntity(
        0,
        weather.city.city,
        weather.temperature,
        weather.condition)
}