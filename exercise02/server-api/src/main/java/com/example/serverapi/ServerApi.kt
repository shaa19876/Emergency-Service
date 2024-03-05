package com.example.serverapi

import com.example.serverapi.models.IncidentDTO
import com.example.serverapi.models.IncidentPostDTO
import com.example.serverapi.models.ZoneDTO
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ServerApi {

  @GET("zones")
  suspend fun getZones(): List<ZoneDTO>

  @GET("incidents")
  suspend fun getIncidents(): List<IncidentDTO>

  @GET("incidents/{zoneId}")
  suspend fun getIncidentByZone(@Path("zoneId") zoneId: Int): List<IncidentDTO>

  @POST("incidents")
  suspend fun postIncident(@Body incident: IncidentPostDTO)
}

fun serverApi(
  baseUrl: String,
  okHttpClient: OkHttpClient,
): ServerApi {
  return retrofit(baseUrl, okHttpClient).create()
}

private fun retrofit(
  baseUrl: String,
  okHttpClient: OkHttpClient,
): Retrofit {
  return Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl(baseUrl)
    .client(okHttpClient)
    .build()
}