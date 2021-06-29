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

fun getDefaultCity() = City("Москва", 55.7558, 37.6173)

fun getRussianCities() = mutableListOf(
    Weather(City("Москва", 55.755826, 37.6173), 1, 2),
    Weather(City("Санкт-Петербург", 59.9342802, 30.3350986), 3, 3),
    Weather(City("Новосибирск", 55.0083526, 82.9357327), 5, 6),
    Weather(City("Екатеринбург", 56.8389261, 60.6057025), 7, 8),
    Weather(City("Нижний Новгород", 56.2965039, 43.936059), 9, 10),
    Weather(City("Казань", 55.8304307, 49.0660806), 11, 12),
    Weather(City("Челябинск", 55.1644419, 61.4368432), 13, 14),
    Weather(City("Омск", 54.9884804, 73.3242361), 15, 16),
    Weather(City("Ростов-на-Дону", 47.2357137, 39.701505), 17, 18),
    Weather(City("Уфа", 54.7387621, 55.9720554), 19, 20)
)
