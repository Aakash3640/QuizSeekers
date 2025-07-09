package local.ak.quizseeekers.domain.usecase

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import local.ak.quizseeekers.common.Resource
import local.ak.quizseeekers.domain.model.Quiz
import local.ak.quizseeekers.domain.repository.Quizrepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn


class GetQuizzesusecases(val quizrepository: Quizrepository)     {

    operator fun invoke(
        amount: Int,
        category: Int,
        difficutly: String,
        type: String
    ): Flow<Resource<List<Quiz>>> = flow{

        emit(Resource.Loading())

        try {

            emit(Resource.Success(data = quizrepository.getQuizzes(amount, category, difficutly, type)))

        }
        catch (e: Exception){

            emit(Resource.Error(message = e.message.toString()))

        }
    }.flowOn(Dispatchers.IO)


}