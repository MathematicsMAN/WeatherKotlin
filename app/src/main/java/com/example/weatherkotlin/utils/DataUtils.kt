package com.example.weatherkotlin.utils

import com.example.weatherkotlin.model.FactDTO
import com.example.weatherkotlin.model.Weather
import com.example.weatherkotlin.model.WeatherDTO
import com.example.weatherkotlin.model.getDefaultCity

fun convertDtoToModel(weatherDTO: WeatherDTO): List<Weather> {
    val fact: FactDTO = weatherDTO.fact!!
    return listOf(Weather(getDefaultCity(), fact.temp!!, fact.feels_like!!, fact.condition!!, fact.icon!!))
}