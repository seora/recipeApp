package com.seora.recipeapp.`interface`

import com.seora.recipeapp.entities.Category
import retrofit2.http.GET
import retrofit2.Call

interface GetDataService {

    @GET("/categories.php")
    fun getCategoryList(): Call<List<Category>>

}