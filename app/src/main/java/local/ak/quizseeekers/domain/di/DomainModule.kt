package local.ak.quizseeekers.domain.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import local.ak.quizseeekers.domain.repository.Quizrepository
import local.ak.quizseeekers.domain.usecase.GetQuizzesusecases
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object DomainModule {


    @Provides
    @Singleton
    fun provideGetQuizzesUseCase(quizreposity : Quizrepository ): GetQuizzesusecases{
        return GetQuizzesusecases(quizreposity)
    }
}