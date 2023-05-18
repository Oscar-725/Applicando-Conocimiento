package com.example.applicandoconocimiento.repository

import com.example.applicandoconocimiento.data.database.dao.LessonDao
import com.example.applicandoconocimiento.data.database.entities.Lesson


class LessonRepository (private val lessonDao: LessonDao) {


    suspend fun insert(lesson: Lesson): Long{
        return lessonDao.insert(lesson)
    }

    suspend fun getByTitulo (titulo : String): Lesson {
        return lessonDao.getByTitulo(titulo)
    }

    suspend fun getLessonTitles(): List<String>? {
        return lessonDao.getTitulos()
    }

    suspend fun getAll(): List<Lesson> {
        return lessonDao.getAll()
    }


}