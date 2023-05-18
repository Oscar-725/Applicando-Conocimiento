package com.example.applicandoconocimiento.data.database.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.applicandoconocimiento.data.database.dao.LessonDao
import com.example.applicandoconocimiento.data.database.entities.Lesson

@Database(
    entities = [Lesson::class],
    version = 1
)
abstract class LessonDb: RoomDatabase() {

    abstract fun lessonDao(): LessonDao

    companion object{
        @Volatile
        private var INSTANCE: LessonDb? = null

        fun getDataBase (context: Context) : LessonDb {
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(context.applicationContext, LessonDb::class.java,
                "Lessons").build()
                INSTANCE = instance
                instance
            }
        }


    }

}