package com.evertonprdo.budgetwise.room

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.evertonprdo.budgetwise.data.local.LocalCategory.categories
import com.evertonprdo.budgetwise.data.room.AppDatabase

object RoomInMemoryDatabase {
    fun getDatabase(): AppDatabase {
        val appDatabase = Room
            .inMemoryDatabaseBuilder(
                ApplicationProvider.getApplicationContext(),
                AppDatabase::class.java
            )
            .allowMainThreadQueries()
            .build()

        seedCategories(appDatabase)
        return appDatabase
    }

    private fun seedCategories(database: AppDatabase) {
        val values = categories
            .joinToString(separator = ",", postfix = ";") {
                "(${it.id}, '${it.name}', '${it.displayName}', '${it.color}', '${it.iconKey}')"
            }

        database.openHelper.writableDatabase.execSQL(
            """
                INSERT INTO categories (id, name, display_name, color, icon_key)
                VALUES $values
            """
        )
    }
}