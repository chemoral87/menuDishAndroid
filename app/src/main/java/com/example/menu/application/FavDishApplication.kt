package com.example.menu.application

import android.app.Application
import com.example.menu.model.database.FavDishRepository
import com.example.menu.model.database.FavDishRoomDatabase

class FavDishApplication : Application() {
    private val database by lazy { FavDishRoomDatabase.getDatabase((this@FavDishApplication)) }

    val respository by lazy { FavDishRepository(database.favDishDao())}
}