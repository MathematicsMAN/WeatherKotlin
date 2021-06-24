package com.example.weatherkotlin.repository

import com.example.weatherkotlin.model.Weather
import com.example.weatherkotlin.room.HistoryDao
import com.example.weatherkotlin.utils.convertHistoryEntityToWeather
import com.example.weatherkotlin.utils.convertWeatherToEntity

class LocalRepositoryImpl(private val localDataSource: HistoryDao) : LocalRepository {
    override fun getAllHistory(): List<Weather> {
        return convertHistoryEntityToWeather(localDataSource.all())
    }

    override fun saveEntity(weather: Weather) {
        localDataSource.insert(convertWeatherToEntity(weather))
    }
}