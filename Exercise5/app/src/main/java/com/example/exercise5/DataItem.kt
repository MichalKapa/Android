package com.example.exercise5

import kotlin.random.Random

class DataItem {
    var text_main : String = "Default text"
    var text_2 : String = "Default text"
    var item_value : Int = Random.nextInt(0, 5)
    var item_value2: Int = 0
    var item_type : Boolean = Random.nextBoolean()
    var item_checked : Boolean = Random.nextBoolean()
    constructor()
    constructor(num: Int) : this() {
        text_main = "number: $num"
        text_2 = "text2"
    }
}