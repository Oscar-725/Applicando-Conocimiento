package com.example.applicandoconocimiento.ui.viewmodel

import android.app.Application
import androidx.room.Room
import com.example.applicandoconocimiento.data.database.database.LessonDb

class LessonApp: Application() {

    val database by lazy { LessonDb.getDataBase(this) }

    companion object {
        lateinit var instance: LessonApp
            private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

}