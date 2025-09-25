package cis3334.kotlin_pizzaorderstart

// --- File: PizzaDatabase.kt ---
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [Pizza::class], version = 1, exportSchema = false)
abstract class PizzaDatabase : RoomDatabase() {
    abstract fun pizzaDao(): PizzaDao


    companion object {
        @Volatile private var INSTANCE: PizzaDatabase? = null
        fun get(context: Context): PizzaDatabase = INSTANCE ?: synchronized(this) {
            INSTANCE ?: Room.databaseBuilder(
                context.applicationContext,
                PizzaDatabase::class.java,
                "pizza_db"
            ).fallbackToDestructiveMigration().build().also { INSTANCE = it }
        }
    }
}