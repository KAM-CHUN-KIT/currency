package com.rev.currency.application

import android.app.Application
import com.rev.currency.di.component.DaggerRevApplicationComponent
import com.rev.currency.di.component.RevApplicationComponent
import com.rev.currency.di.module.ApplicationModule
import com.rev.currency.manager.DataManager
import com.rev.currency.repository.RepositoryConfig
import javax.inject.Inject

class RevApplication : Application() {
    init {
        instance = this
    }

    private val component: RevApplicationComponent by lazy {
        DaggerRevApplicationComponent.builder().applicationModule(ApplicationModule(this)).build()
    }

    @Inject
    lateinit var mDataManager: DataManager

    companion object {
        private var instance: RevApplication? = null
        private var dataManager: DataManager ?= null

        fun getDataManager(): DataManager{
            return dataManager!!
        }
    }

    override fun onCreate() {
        super.onCreate()
        componentDIConfig()
        networkConfig()
    }

    private fun networkConfig(){
        RepositoryConfig(applicationContext)
    }

    private fun componentDIConfig(){
        component.inject(this)
        dataManager = mDataManager
    }


}