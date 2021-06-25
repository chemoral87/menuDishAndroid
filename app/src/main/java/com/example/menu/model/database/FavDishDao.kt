package com.example.menu.model.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.menu.model.entities.FavDish
import kotlinx.coroutines.flow.Flow


@Dao
interface FavDishDao {
    @Insert
    suspend fun insertFavDishDetails(favDish: FavDish)

    @Query("SELECT * FROM fav_dishes_table order by id desc")
    fun getAllDishesList(): Flow<List<FavDish>>
}