package com.example.exercise5

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlin.random.Random

@Entity(tableName = "item_table")
class DBItem {
    @PrimaryKey(autoGenerate = true)
    var id = 0
    var text_main: String? = null
    var text_2: String? = null
    @ColumnInfo(name = "rating")
    var item_value = 0
    @ColumnInfo(name = "age")
    var item_value2 = 0
    @ColumnInfo(name = "sex")
    var item_type = false
    constructor()
    constructor(num: Int) : this(){
        text_main = "Item name " + num
        text_2 = "Default text" + num
        item_value = Random.nextInt(0, 5)
        item_value2 = Random.nextInt(0, 100)
        item_type = Random.nextBoolean()
    }
}