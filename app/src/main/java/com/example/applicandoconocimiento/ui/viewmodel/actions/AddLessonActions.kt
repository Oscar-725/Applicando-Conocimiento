package com.example.applicandoconocimiento.ui.viewmodel.actions

import com.example.applicandoconocimiento.data.database.entities.Lesson

sealed class AddLessonActions {

    data class ShowLastData(val result: Lesson?): AddLessonActions()
    //data class InserLesson (val result: Lesson?) : AddLessonActions()
    data class ErrorLesson (val result: String) : AddLessonActions()
    object ReportSuccessInsert: AddLessonActions()
}