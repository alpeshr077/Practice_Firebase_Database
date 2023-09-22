package com.alpesh1.practice_firebase_database

class ModelClass {

    lateinit var key : String
    lateinit var name: String
    lateinit var surname: String

    constructor(){

    }

    constructor(key : String,name: String, surname: String) {
        this.key = key
        this.name = name
        this.surname = surname
    }

}