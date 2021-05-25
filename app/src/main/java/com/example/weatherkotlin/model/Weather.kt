package com.example.weatherkotlin.model

class Weather(
    val city: City = getDefaultCity(),
    val temperature: Int = 22,
    val feelsLike: Int = 20
)

fun getDefaultCity() = City("Москва", 55.7558, 37.6173)
