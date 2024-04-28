package eu.tutorials.myrecipeapp

import android.media.Image
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter


// This display normal dishes until certain event triggers and callback function become activated and implementaion
// given by recipe app which switches from recipeScreen to detailRecipe Screen
@Composable
fun RecipeScreen(modifier: Modifier = Modifier, navigateToDetail: (Category)->Unit,
                 viewstate: MainViewModel.RecipeState) {

 //  val viewstate by recipeViewModel.categoriesState  // using recipe model refrence getting the state recipe

    Box(modifier = Modifier.fillMaxSize()) {
        when {
            viewstate.loading ->{
                CircularProgressIndicator(modifier.align(Alignment.Center)) // different way of writing modifier
            }
            viewstate.error != null -> {
                Text(text = "ERROR OCCURRED")
            }
            else -> {
                //Display Categories
                CategoryScreen(categories = viewstate.list, navigateToDetail)  // passing the Categories Response
                                                         // list(String) that we get from server as Json
            }


        }

    }

}
// check mainviewmodel data class recipe state
// it has list of category
@Composable
fun CategoryScreen(categories: List<Category>, navigateToDetail: (Category)->Unit) {
    LazyVerticalGrid(GridCells.Fixed(2), modifier = Modifier.fillMaxSize()) {
        items(categories) {

            //categories represent list of category and category alone is single category
            //single category is passing one at a time
            category ->
            CategoryItem(category = category, navigateToDetail )
        }
    }
}

// it is used for category data class which is inside list of category
@Composable
fun CategoryItem(category: Category, navigateToDetail: (Category)->Unit) {
    Column(modifier = Modifier
        .padding(8.dp)
        .fillMaxSize()
        .clickable { navigateToDetail(category) },  // here we passing random category item (dish)
                                                    // that is visible in Ui and anyone can demand for detailscreen

        horizontalAlignment = Alignment.CenterHorizontally) {


      Image(
          painter = rememberAsyncImagePainter(category.strCategoryThumb), // loading image from the url asynchronously
          contentDescription = null,
          modifier = Modifier
              .fillMaxSize()
              .aspectRatio(1f))

    Text(
        text = category.strCategory,
        color = Color.Black,
        style = TextStyle(fontWeight = FontWeight.Bold),
        modifier = Modifier.padding(top = 4.dp)
        )



    }
}