package local.ak.quizseeekers.data

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import local.ak.quizseeekers.data.remote.QuizApi
import local.ak.quizseeekers.data.respository.QuizrepositoryImpl
import local.ak.quizseeekers.domain.repository.Quizrepository
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object DataModule {


    @Provides
    @Singleton
    fun provideQuizApi(): QuizApi{
        return Retrofit.Builder()
            .baseUrl("https://opentdb.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(QuizApi::class.java)
    }

    @Provides
    @Singleton
    fun provideQuizRepo(quizApi: QuizApi): Quizrepository{
        return QuizrepositoryImpl(quizApi)
    }
}



