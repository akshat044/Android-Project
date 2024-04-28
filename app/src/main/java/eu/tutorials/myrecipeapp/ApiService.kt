package eu.tutorials.myrecipeapp

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

private val retrofit = Retrofit.Builder().baseUrl("https://www.themealdb.com/api/json/v1/1/")
    .addConverterFactory(GsonConverterFactory.create()) // first creating retrofit object
    .build()

val recipreService = retrofit.create(ApiService::class.java) // then creating APi service object

interface ApiService{
    @GET("categories.php")
    suspend fun getCategory():CategoriesResponse  // get category function has data from the server type of CategoriesResponse
    // : represent return type


}