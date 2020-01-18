package com.rev.currency.di.component

import com.rev.currency.application.RevApplication
import com.rev.currency.di.module.ApplicationModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class])
interface RevApplicationComponent{
    fun inject(app: RevApplication)
}