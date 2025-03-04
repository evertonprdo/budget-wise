package com.evertonprdo.budgetwise

import android.app.Application
import com.evertonprdo.budgetwise.data.AppContainer
import com.evertonprdo.budgetwise.data.AppDataContainer

class BudgetWiseApplication : Application() {

    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this)
    }
}