package no.wahid.jottacloud.model;

import retrofit2.Call;
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET;
import retrofit2.http.Path;

interface JottaApiService {
    @GET("public/ja7td7s3")
    suspend fun getAlbum(): Response<Album>

    companion object {
        fun create(): JottaApiService {

            return Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://api.jottacloud.com/photos/v1/").build()
                .create(JottaApiService::class.java)

        }
    }
}