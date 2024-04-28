package eu.tutorials.myrecipeapp
// this is a part whose object create so we get data from server with the help of api service
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val _categorieState = mutableStateOf(RecipeState()) // type of data class
    val categoriesState: State<RecipeState> = _categorieState

    init {
        fetchCategories()  // moment when our main viewmodel object created inistallize or we say call fetchCategories function
    }

    private fun fetchCategories() {
        // viemodel scope make sure to our ui not freeze when we are fetching data from the server
        viewModelScope.launch {
            // even we get data from the server there my be error to avoid such case
            try {  // we use try that what if data from the server is corrupted


                // recipe service has list inside CategoriesResponse data class
                 val response = recipreService.getCategory()   // we are calling object refrence variable that store interface object has now data
                _categorieState.value = _categorieState.value.copy( // from the server
                    list = response.categories,// response.categories represents the list of categories obtained from the API response.
                    loading = false,
                    error = null
                )

            }catch (e: Exception) {
                _categorieState.value =  _categorieState.value.copy( //If there's an exception (e.g., a network error), it updates the state with an error message.
                    loading = false,                                  // by making all the value same just changing the error message from null to something
                    error = "Error fetching Categories ${e.message}"
                )
            }

        }
    }

    data class RecipeState(
        val loading: Boolean = true,
        val list: List<Category> = emptyList(),
        val error: String? = null
    )
}