package eu.tutorials.myrecipeapp

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun RecipeApp(navController: NavHostController) {
    val recipeViewModel: MainViewModel = viewModel()
    val viewstate by recipeViewModel.categoriesState
    
    NavHost(navController = navController, startDestination = Screen.RecipeScreen.route) {
        composable(route = Screen.RecipeScreen.route) {
            RecipeScreen(viewstate = viewstate, navigateToDetail =  {
                // This part is responsible for passing data from the current screen to the digital screen.
                // It utilizes the savedStateHandle, which is a component of the Compose navigation framework.


     // mere hisab se below line mtlab jo category click krne mein aya hai usko same krlo as a cat refrence then use it detail screen to pass category
                navController.currentBackStackEntry?.savedStateHandle?.set("cat",it)  // ?. represent null value also
                navController.navigate(Screen.DetailScreen.route)                   // store in variable
            })
        }
       composable(route = Screen.DetailScreen.route) {
           val category = navController.previousBackStackEntry?.savedStateHandle?.
            get<Category>("cat") ?: Category("","", "", "") // if empty crating empty category
           CategoryDetailScreen(category = category)
       }
        
    }
    
}
 