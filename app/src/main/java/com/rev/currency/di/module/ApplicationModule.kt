package com.rev.currency.di.module

import com.rev.currency.application.RevApplication
import com.rev.currency.manager.DataManager
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule(val app: RevApplication) {
    @Provides
    @Singleton
    fun provideApp() = app

    @Provides
    @Singleton
    fun provideDataManager(): DataManager {
        return DataManager()
    }
}