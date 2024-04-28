package eu.tutorials.myrecipeapp

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Category(val idCategory: String,
    val strCategory: String,
    val strCategoryThumb: String, // stores url of image
    val strCategoryDescription: String
    ):Parcelable


data class CategoriesResponse(val categories: List<Category>)

//idCategory": "1",
//"strCategory": "Beef",
//"strCategoryThumb": "https://www.themealdb.com/images/category/beef.png",
//"strCategoryDescription"