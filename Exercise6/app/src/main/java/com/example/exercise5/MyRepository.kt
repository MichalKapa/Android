package com.example.exercise5

import android.content.Context

class MyRepository(context: Context) {

    private var dataList: MutableList<DBItem>? = null
    private var myDao: MyDao
    private var db: MyDB

    companion object{
        private var R_INSTANCE: MyRepository? = null
        fun getinstance(context: Context): MyRepository {
            if (R_INSTANCE == null){
                R_INSTANCE = MyRepository(context)
            }
            return R_INSTANCE as MyRepository
        }
    }

    init {
        db = MyDB.getDatabase(context)!!
        myDao = db.myDao()!!
//         addItem(DBItem(1))
//         addItem(DBItem(2))
    }

    fun getData(): MutableList<DBItem>? {
        dataList = myDao.getAllData()
        return dataList
    }
    fun addItem(item: DBItem?) : Boolean {
        return myDao.insert(item) >= 0
    }
    fun deleteItem(item: DBItem?) : Boolean {
        return myDao.delete(item) > 0
    }
    fun updateItem(item: DBItem?, index: Int?): Boolean {
        myDao.update(myDao.getAllData()?.get(index!!), item)
        return true
    }
}