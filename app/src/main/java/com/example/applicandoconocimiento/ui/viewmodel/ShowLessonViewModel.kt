package com.example.applicandoconocimiento.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.applicandoconocimiento.data.database.entities.Lesson
import com.example.applicandoconocimiento.repository.LessonRepository
import com.example.applicandoconocimiento.ui.viewmodel.actions.ShowLessonActions
import kotlinx.coroutines.launch

class ShowLessonViewModel : ViewModel() {

    val repository = LessonRepository(LessonApp.instance.database.lessonDao())

    private var lesson: Lesson? = null

    private val actions = MutableLiveData<ShowLessonActions>()
    fun getActionLiveData() = actions as LiveData<ShowLessonActions>


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


    fun showLesson(titulo: String) {

        viewModelScope.launch {
            val data = repository.getByTitulo(titulo)
            lesson = data
            actions.value  = ShowLessonActions.ShowLesson(data)
        }
    }

    fun messageError (message: String){
        actions.value = ShowLessonActions.ErrorLesson(message)
    }

    fun deleteLesson (){
        viewModelScope.launch {
            if (lesson!=null) {
                repository.deleteLesson(lesson!!)
                lesson = null
                actions.value  = ShowLessonActions.ShowLesson(lesson)
                getLessonTitles()

            } else {
                actions.value = ShowLessonActions.ErrorLesson("No haz seleccionado ninguna leccion")
            }
            //actions.value = ShowLessonActions.DeleteLesson(lesson)
        }
    }
}