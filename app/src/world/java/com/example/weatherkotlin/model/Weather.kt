package com.example.weatherkotlin.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Weather(
    val city: City = getDefaultCity(),
    val temperature: Int = 22,
    val feelsLike: Int = 20,
    val condition: String = "sunny",
    val icon: String? = "bkn_n"
): Parcelable

fun getDefaultCity() = City("Минск", 53.9045398, 27.5615244)

fun getWorldCities() = listOf(
        Weather(City("Лондон", 51.50853, -0.12574), 1, 2),
        Weather(City("Токио", 35.6895, 139.69171), 3, 4),
        Weather(City("Париж", 48.85341, 2.3488), 5, 6),
        Weather(City("Берлин", 52.5200066, 13.404954), 7, 8),
        Weather(City("Рим", 41.9027835, 12.4963655), 9, 10),
        Weather(City("Минск", 53.9045398, 27.5615244), 11, 12),
        Weather(City("Стамбул", 41.0082376, 28.9783589), 13, 14),
        Weather(City("Вашингтон", 38.9071923, -77.0368707), 15, 16),
        Weather(City("Киев", 50.4501, 30.5234), 17, 18),
        Weather(City("Пекин", 39.9042, 116.4073963), 19, 20)
    )

