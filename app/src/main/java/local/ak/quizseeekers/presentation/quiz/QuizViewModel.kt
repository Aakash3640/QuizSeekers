package local.ak.quizseeekers.presentation.quiz

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import local.ak.quizseeekers.common.Resource
import local.ak.quizseeekers.domain.model.Quiz
import local.ak.quizseeekers.domain.usecase.GetQuizzesusecases


@HiltViewModel
class QuizViewModel @Inject constructor(private val getQuizzesusecases: GetQuizzesusecases): ViewModel() {


    private val _quizlist = MutableStateFlow(statequizScreen())

    val quizlist = _quizlist


    fun onEvent(event : EventQuizScreen){
        when(event){
            is EventQuizScreen.GetQuizzes ->{

                getQuizzes(event.numofquiz,event.category,event.difficlty,event.type)
            }
            is EventQuizScreen.SetoptionSelected -> {
                updateQuizStateList(event.quizStateIndex,event.selectedOption)
            }

            else ->{}
        }
    }

    private fun updateQuizStateList(quizStateIndex: Int, selectedOption: Int) {

        val updatedQuizstateList = mutableListOf<QuizState>()
        quizlist.value.quizstate.forEachIndexed { index, quizState ->

            updatedQuizstateList.add(
                if(quizStateIndex == index){
                    quizState.copy(selectedoption = selectedOption)
                }
                else quizState

            )
        }
        _quizlist.value = quizlist.value.copy(quizstate = updatedQuizstateList)


        updateScore(_quizlist.value.quizstate[quizStateIndex])
    }

    private fun updateScore(quizState: QuizState) {

        if(quizState.selectedoption != -1){
            val correctAnswer = quizState.quiz?.correct_answer
            val selectedAnswer = quizState.selectedoption?.let {
                quizState.shuffledoption[it].replace("&quot;", "\"").replace("&#039;", "'")
            }

            if (correctAnswer == selectedAnswer){
                val previousScore = _quizlist.value.score
                _quizlist.value = quizlist.value.copy(score = previousScore +1 )
            }

        }


    }


    private var hasLoadedOnce = false
    private fun getQuizzes(amount: Int, category:Int, difficulty: String, type: String){

        if (hasLoadedOnce) return

        viewModelScope.launch {
            getQuizzesusecases(amount, category, difficulty, type).collect { resource ->
                when (resource) {
                    is Resource.Loading -> {
                        _quizlist.value = statequizScreen(isLoading = true)
                    }

                    is Resource.Success -> {
                        val listofquizstate: List<QuizState> = getListOfQuizState(resource.data)
                        _quizlist.value = statequizScreen(quizstate = listofquizstate)
                        hasLoadedOnce = true
                    }

                    is Resource.Error -> {
                        _quizlist.value = statequizScreen(error = resource.message.toString())
                        hasLoadedOnce = true
                    }
                }
            }
        }

    }

    private fun getListOfQuizState(data: List<Quiz>?): List<QuizState> {
        val listofquizstate  = mutableListOf<QuizState>()

        for (quiz in data!!){

            val shuffledoptions = mutableListOf<String>().apply {

                add(quiz.correct_answer)
                addAll(quiz.incorrect_answers)
                shuffle()
            }
            listofquizstate.add(QuizState(quiz,shuffledoptions,-1))

        }

        return listofquizstate
    }
}