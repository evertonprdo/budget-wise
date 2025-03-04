package com.evertonprdo.budgetwise.room

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.evertonprdo.budgetwise.data.room.AppDatabase
import com.evertonprdo.budgetwise.data.room.dao.TransactionDao
import com.evertonprdo.budgetwise.data.room.entities.Transaction
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class TransactionDaoTest {
    private lateinit var transactionDao: TransactionDao
    private lateinit var database: AppDatabase

    private val transaction1 = Transaction(
        id = 1,
        type = "in",
        cents = 100,
        date = 123,
        description = "test-1",
        categoryId = 1
    )

    private val transaction2 = Transaction(
        id = 2,
        type = "out",
        cents = 1010,
        date = 12345,
        description = "test-2",
        categoryId = 1
    )

    @Before
    fun createDb() {
        database = RoomInMemoryDatabase.getDatabase()
        transactionDao = database.transactionDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        database.close()
    }

    private suspend fun addOneTransactionToDb() {
        transactionDao.insert(transaction1)
    }

    private suspend fun addTwoTransactionToDb() {
        transactionDao.insert(transaction1)
        transactionDao.insert(transaction2)
    }

    @Test
    @Throws(Exception::class)
    fun daoInsert_InsertTransactionIntoDB() = runBlocking {
        addOneTransactionToDb()
        val allTransactions = transactionDao.getAll().first()

        Assert.assertEquals(1, allTransactions[0].id)
        Assert.assertEquals(transaction1.copy(id = 1), allTransactions[0])
    }

    @Test
    @Throws(Exception::class)
    fun daoGetAll_returnAllTransactionsFromDB() = runBlocking {
        addTwoTransactionToDb()
        val allTransactions = transactionDao.getAll().first()
        Assert.assertEquals(transaction1, allTransactions[0])
        Assert.assertEquals(transaction2, allTransactions[1])
    }

    @Test
    @Throws(Exception::class)
    fun daoUpdate_updatesTransactionInDB() = runBlocking {
        addTwoTransactionToDb()

        val newTransaction = transaction1.copy(description = "update-test")
        transactionDao.update(newTransaction)
        val transaction = transactionDao.findFirst(1).first()

        Assert.assertEquals(newTransaction, transaction)
    }

    @Test
    @Throws(Exception::class)
    fun daoDelete_deletesAllTransactionsFromDB() = runBlocking {
        addTwoTransactionToDb()

        transactionDao.delete(transaction1)
        transactionDao.delete(transaction2)

        val allTransactions = transactionDao.getAll().first()
        Assert.assertTrue(allTransactions.isEmpty())
    }

    @Test
    @Throws(Exception::class)
    fun daoGetItem_returnsItemFromDB() = runBlocking {
        addOneTransactionToDb()
        val item = transactionDao.findFirst(1)
        Assert.assertEquals(item.first(), transaction1)
    }
}