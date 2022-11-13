package com.example.roomdemo.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [UseEntity::class], version = 1, exportSchema = false)
abstract class RoomAppDb : RoomDatabase() {
    abstract fun userDao(): UserDao?

    companion object {
        @Volatile
        private var INSTANCE: RoomAppDb? = null
        fun getAppDatabase(context: Context): RoomAppDb {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder<RoomAppDb>(
                    context.applicationContext,
                    RoomAppDb::class.java,
                    "user_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}