package com.example.weatherkotlin.repository

import com.example.weatherkotlin.model.WeatherDTO
import retrofit2.Callback

class DetailsRepositoryImpl(private val remoteDataSource: RemoteDataSource) : DetailsRepository {
    override fun getWeatherDetailsFromServer(
//        requestLink: String,
        lat: Double,
        lon: Double,
        callback: Callback<WeatherDTO>
    ) {
        remoteDataSource.getWeatherDetails(
//            requestLink,
            lat,
            lon,
            callback)
    }
}
