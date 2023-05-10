package ru.madmax.vktestcase2.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.madmax.vktestcase2.data.repository.FileRepositoryImpl
import ru.madmax.vktestcase2.domain.repository.FileRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRepository(): FileRepository {
        return FileRepositoryImpl()
    }

}