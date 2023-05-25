package com.example.applicandoconocimiento.ui.viewmodel.actions

import com.example.applicandoconocimiento.data.database.entities.Lesson

sealed class ShowLessonActions {

    data class ShowLesson(val result: Lesson? ) : ShowLessonActions()
    data class ShowTitles(val result: List<String>) : ShowLessonActions()
    data class ShowLastData(val result: Lesson?): ShowLessonActions()
    data class ErrorLesson (val result: String) : ShowLessonActions()

}