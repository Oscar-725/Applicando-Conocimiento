package com.example.applicandoconocimiento.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.applicandoconocimiento.data.database.entities.Lesson

@Dao
interface LessonDao {

    @Query ("SELECT * FROM Lesson WHERE titulo = :titulo")
    suspend fun getByTitulo (titulo: String) : Lesson

    @Query ("SELECT * FROM Lesson")
    suspend fun getAll () : List<Lesson>

    @Query ("SELECT titulo FROM Lesson ORDER BY titulo ASC")
    suspend fun getTitulos () : List<String>?

    @Insert
    suspend fun insert (lesson : Lesson): Long

    @Update
    suspend fun update (lesson: Lesson)

    @Delete
    suspend fun delete (lesson: Lesson)
}