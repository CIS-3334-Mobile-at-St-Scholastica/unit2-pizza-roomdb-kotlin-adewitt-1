package cis3334.kotlin_pizzaorderstart

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface PizzaDao {
    @Insert
    suspend fun insertPizza(pizza: Pizza)

    @Query("SELECT * FROM pizzas")
    fun getAll(): List<Pizza>

    @Query("DELETE FROM pizzas")
    suspend fun deleteAll()
}