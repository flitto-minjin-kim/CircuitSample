package com.minjin.sample.di

import com.minjin.sample.data.EmailRepository
import com.minjin.sample.data.EmailRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
internal abstract class DataModule {

    @Singleton
    @Binds
    abstract fun bindEmailRepository(emailRepositoryImpl: EmailRepositoryImpl): EmailRepository

}