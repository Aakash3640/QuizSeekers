package local.ak.quizseeekers.presentation.home

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow

class HomeScreenViewModal : ViewModel() {


    private val _homestate   = MutableStateFlow(StateHomeScreen())
    val homestate = _homestate

    fun onEvent(event: EventHomeScreen){
        when(event){

            is EventHomeScreen.SetnumberofQuiz ->{
                _homestate.value = homestate.value.copy(numberofQuiz = event.numberofquiz)

            }

            is EventHomeScreen.SetQuizcategory ->{

                _homestate.value = homestate.value.copy(category = event.quizcategory)

            }

            is EventHomeScreen.SetQuizdiffulty ->{

                _homestate.value = homestate.value.copy(difficulty = event.quizdiffulty)
            }

            is EventHomeScreen.SetQuiztype ->{
                _homestate.value = homestate.value.copy(type = event.quiztype)

            }

        }
    }
}