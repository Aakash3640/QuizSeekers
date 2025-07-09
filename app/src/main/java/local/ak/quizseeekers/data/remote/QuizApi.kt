package local.ak.quizseeekers.data.remote

import local.ak.quizseeekers.data.remote.datatransferobject.QuizResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface QuizApi {

    @GET("api.php")
    suspend fun getQiuzzes(

        @Query("amount") amount : Int,
        @Query("category") category : Int,
        @Query("difficulty") difficulty : String,
        @Query("type") type : String
    ) : QuizResponse
}