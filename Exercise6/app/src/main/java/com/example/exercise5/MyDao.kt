package com.example.exercise5

import androidx.room.*

@Dao
interface MyDao {
    @Query("SELECT * FROM item_table ORDER BY id ASC")
    fun getAllData(): MutableList<DBItem>?
    @Query("DELETE FROM item_table")
    fun deleteAll()
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(item: DBItem?) : Long
    @Delete
    fun delete(item: DBItem?) : Int
    @Update
    fun update(old: DBItem?, new: DBItem?)
}