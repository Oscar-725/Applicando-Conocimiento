package com.example.applicandoconocimiento.data.database.entities

import android.graphics.Bitmap
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Lesson(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val titulo: String,
    @ColumnInfo(typeAffinity = ColumnInfo.BLOB)
    val imagen: ByteArray,
    val comentario: String
    )

