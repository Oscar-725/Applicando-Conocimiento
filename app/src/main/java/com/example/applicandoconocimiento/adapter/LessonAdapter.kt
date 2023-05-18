package com.example.applicandoconocimiento.adapter

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.graphics.drawable.toBitmap
import androidx.recyclerview.widget.RecyclerView
import com.example.applicandoconocimiento.R
import com.example.applicandoconocimiento.data.database.entities.Lesson
import com.example.applicandoconocimiento.databinding.ItemLessonBinding

class LessonAdapter (private val lesson: Lesson): RecyclerView.Adapter<LessonAdapter.LessonViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LessonViewHolder {
       val layoutInflater = LayoutInflater.from(parent.context)
        return LessonViewHolder(layoutInflater.inflate(R.layout.item_lesson,parent,false))
    }

    override fun getItemCount(): Int = lesson.id

    override fun onBindViewHolder(holder: LessonViewHolder, position: Int) {
        val item = lesson
        holder.render(item)

    }


    class LessonViewHolder (view: View) : RecyclerView.ViewHolder(view) {

        private val binding = ItemLessonBinding.bind(view)
        val titleLesson = binding.tvTitleLesson
        val comentLesson = binding.tvComentsLesson
        val imagenLesson = binding.ivLesson

        fun render (lessonModel : Lesson){
            titleLesson.text = lessonModel.titulo
            comentLesson.text = lessonModel.comentario

            val bmp: Bitmap = BitmapFactory.decodeByteArray(lessonModel.imagen,0,lessonModel.imagen.size)

            imagenLesson.setImageBitmap(
                Bitmap.createScaledBitmap(bmp, imagenLesson.width, imagenLesson.height, false)
            )
        }
    }
}