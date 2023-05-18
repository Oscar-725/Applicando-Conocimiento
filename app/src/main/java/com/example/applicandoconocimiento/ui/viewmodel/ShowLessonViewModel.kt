package com.example.applicandoconocimiento.ui.viewmodel

import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.applicandoconocimiento.data.database.entities.Lesson
import com.example.applicandoconocimiento.repository.LessonRepository
import kotlinx.coroutines.launch

class ShowLessonViewModel : ViewModel() {

    val repository = LessonRepository(LessonApp.instance.database.lessonDao())

    private val actions = MutableLiveData<ShowLessonActions>()
    fun getActionLiveData() = actions as LiveData<ShowLessonActions>

    fun saveLesson(lesson: Lesson) {
        viewModelScope.launch {
            val value = repository.insert(lesson)
            if(value >= 1) {
                actions.value = ShowLessonActions.ReportSuccessInsert
            } else {
                messageError("Error al insertar")
            }

        }
    }

    fun getLessonTitles() {
        viewModelScope.launch {
            val titles = repository.getLessonTitles()
            if (titles != null) {
                actions.value = ShowLessonActions.ShowTitles(titles)
            } else {
                messageError("No se encontraron Lecciones")
            }
        }
    }

    fun getLastData() {
        viewModelScope.launch {
            val data = repository.getAll()
            if (data != null) {
                actions.value = ShowLessonActions.ShowLastData(data.lastOrNull())
            } else {
                messageError("No se encontraron Datos")
            }
        }
    }

    fun showLesson(titulo: String) {
        viewModelScope.launch {
            val data = repository.getByTitulo(titulo)
            actions.value  = ShowLessonActions.ShowLesson(data)
        }
    }

    fun messageError (message: String){
        actions.value = ShowLessonActions.ErrorLesson(message)
    }
}