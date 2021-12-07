package com.example.myapplication.services

import com.example.myapplication.objects.Place
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Class used for connecting to the Mapbox service.
 */
class MapboxService {

    companion object GeoCoding {
        // Base URL of the API
        private const val BASE_URL = "https://api.mapbox.com/"

        // Acess token. Replace with your own in future projects.
        const val ACCESS_TOKEN = "pk.eyJ1IjoicGludmVudGFkbyIsImEiOiJja3cwd2wzZjQxNHl0Mm5vYmNzbjdlemc4In0.7JJaksZTGFSR85coY8HNWg"

        // Moshi object used to parse JSON.
        private val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

        // Retrofit object for retrieving data from the internet. We use
        // Moshi to parse data returned by the API
        private val retrofit = Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .baseUrl(BASE_URL)
            .build()

        // List of API endpoints you want to access
        interface MapboxGeoCodingApiService {
            @GET("geocoding/v5/mapbox.places/market.json")
            fun getLocation(@Query(value = "proximity") proximity: String = "-117.88522078474725,33.880650097912564",
                          @Query("access_token") token: String = ACCESS_TOKEN): Call<Place>
        }


        object Api {
            /**
             * By lazy allows us to create an uninitialized constant proprerty.
             * It will be assigned a value returned by the closure when it is
             * accessed the first time thereby saving processing time.
             */
            val retrofitService : MapboxGeoCodingApiService by lazy {
                retrofit.create(MapboxGeoCodingApiService::class.java) }
        }
    }
}

