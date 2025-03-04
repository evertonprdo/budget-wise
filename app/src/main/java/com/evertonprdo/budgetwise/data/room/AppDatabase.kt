package com.evertonprdo.budgetwise.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.evertonprdo.budgetwise.data.room.dao.TransactionDao
import com.evertonprdo.budgetwise.data.room.entities.Category
import com.evertonprdo.budgetwise.data.room.entities.Transaction

@Database(
    entities = [Transaction::class, Category::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun transactionDao(): TransactionDao

    // @feature: management categories
    // abstract fun transactionWithCategoryDao(): TransactionWithCategoryDao

    companion object {
        @Volatile
        private var instance: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {

            return instance ?: synchronized(this) {
                Room
                    .databaseBuilder(
                        context,
                        AppDatabase::class.java,
                        "budget_wise_database"
                    )
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { instance = it }
            }
        }
    }
}