package com.evertonprdo.budgetwise.room

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.evertonprdo.budgetwise.data.room.AppDatabase
import com.evertonprdo.budgetwise.data.room.entities.Category

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

    val categories = listOf(
        Category(
            id = 1,
            name = "test-01",
            displayName = "Test category 1",
            color = "#ad46ff",
            iconKey = "building-store"
        ),
        Category(
            id = 2,
            name = "test-02",
            displayName = "Test category 2",
            color = "#00bc7d",
            iconKey = "refresh"
        )
    )

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