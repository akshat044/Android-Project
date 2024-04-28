package eu.tutorials.myrecipeapp

sealed class Screen(val route: String) {
    object RecipeScreen:Screen("recipescreen") // obj 1 which has recipescreen as String (parametre)
    object DetailScreen:Screen("detailscreen") // obj 2 which has Detailscreen refrence name

}