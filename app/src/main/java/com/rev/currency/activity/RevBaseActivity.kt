package com.rev.currency.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.rev.currency.application.RevApplication
import com.rev.currency.manager.DataManager


open class RevBaseActivity : AppCompatActivity() {
    lateinit var dataManager: DataManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataManager = RevApplication.getDataManager()
    }

}