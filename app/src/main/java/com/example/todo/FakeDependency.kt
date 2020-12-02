package com.example.todo

import android.content.Context

object FakeDependency {

    var context : Context? = null
        get() {
            return field ?: throw Exception("There is no context set")
        }

    //TODO Add repositories

    fun start(context: Context) {

    }
}