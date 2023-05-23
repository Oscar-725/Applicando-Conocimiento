package com.example.applicandoconocimiento.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.applicandoconocimiento.data.database.entities.Lesson
import com.example.applicandoconocimiento.repository.LessonRepository
import com.example.applicandoconocimiento.ui.viewmodel.actions.AddLessonActions
import kotlinx.coroutines.launch

class AddLessonViewModel : ViewModel() {

    val repository = LessonRepository(LessonApp.instance.database.lessonDao())

    private val actions = MutableLiveData<AddLessonActions>()
    fun getActionLiveData() = actions as LiveData<AddLessonActions>

    fun saveLesson(lesson: Lesson) {
        viewModelScope.launch {
            val value = repository.insert(lesson)
            if(value >= 1) {
                actions.value = AddLessonActions.ReportSuccessInsert
            } else {
                messageError("Error al insertar")
            }

        }
    }

    fun getLastData() {
        viewModelScope.launch {
            val data = repository.getAll()
            if (data != null) {
                actions.value = AddLessonActions.ShowLastData(data.lastOrNull())
            } else {
                messageError("No se encontraron Datos")
            }
        }
    }

    fun messageError (message: String){
        actions.value = AddLessonActions.ErrorLesson(message)
    }

}