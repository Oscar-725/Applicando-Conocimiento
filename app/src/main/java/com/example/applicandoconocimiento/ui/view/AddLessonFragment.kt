package com.example.applicandoconocimiento.ui.view

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.drawable.toBitmap
import androidx.lifecycle.ViewModelProvider
import com.example.applicandoconocimiento.data.database.entities.Lesson
import com.example.applicandoconocimiento.databinding.FragmentAddLessonBinding
import com.example.applicandoconocimiento.ui.viewmodel.ShowLessonActions
import com.example.applicandoconocimiento.ui.viewmodel.ShowLessonViewModel
import com.example.module_camera.initCamera
import java.io.ByteArrayOutputStream

class AddLessonFragment : Fragment() {

    private var imagenUri : Uri? = null
    private lateinit var viewModel:ShowLessonViewModel


    private val binding: FragmentAddLessonBinding by lazy {
        FragmentAddLessonBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = binding.root


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(ShowLessonViewModel::class.java)
        bindViewModel()
        setupView()
        viewModel.getLastData()
    }

    private fun bindViewModel() {
        viewModel.getActionLiveData().observe(viewLifecycleOwner, ::handleAction)
    }

    private fun handleAction(actions: ShowLessonActions) {
        when(actions) {
            is ShowLessonActions.ShowLastData -> setUpLastData(actions.result)
            is ShowLessonActions.ShowLesson -> TODO()
            is ShowLessonActions.ShowTitles -> TODO()
            is ShowLessonActions.InserLesson -> saveLesson(actions.result)
            is ShowLessonActions.ErrorLesson -> messageError(actions.result)
            ShowLessonActions.ReportSuccessInsert -> messageError("Exito al insertar")
        }
    }

    private fun messageError(result: String) {
       Toast.makeText(requireContext(),result,Toast.LENGTH_SHORT).show()
    }

    private fun saveLesson(lesson: Lesson?) {
        lesson?.let {


        }
    }

    private fun setUpLastData(model: Lesson?) {
        model?.let {
            //it.imagen(ByteArray) to Bitmap

            val bmp: Bitmap = BitmapFactory.decodeByteArray(it.imagen,0,it.imagen.size)

            binding.editImgCaptura.setImageBitmap(
                Bitmap.createScaledBitmap(bmp, binding.editImgCaptura.width, binding.editImgCaptura.height, false)
            )
        }
    }

    private fun setupView() {
        binding.apply {
            cargarImagen.setOnClickListener {
                val intent = Intent(Intent.ACTION_OPEN_DOCUMENT).apply {
                    addCategory(Intent.CATEGORY_OPENABLE)
                    type = "image/jpeg"
                }
                startActivityForResult(intent, RC_GALERY)
            }
            agregarFoto.setOnClickListener {
                initCamera(requireActivity())
            }
        }

        binding.guardarLeccion.setOnClickListener {

            val bd = binding.editImgCaptura.drawable
            val bit: Bitmap = bd.toBitmap()

            val lesson = Lesson(titulo = binding.etTitulo.text.toString(),
                imagen = bit.toByteArray(), comentario = binding.etComentarios.text.toString())

            viewModel.saveLesson(lesson)
        }
    }

    fun Bitmap.toByteArray():ByteArray{
        ByteArrayOutputStream().apply {
            compress(Bitmap.CompressFormat.JPEG,100,this)
            return toByteArray()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == AppCompatActivity.RESULT_OK){
            if (requestCode == RC_GALERY){
                imagenUri = data?.data
                binding.editImgCaptura.setImageURI(imagenUri)
            }
        }
    }

    companion object {
        private const val RC_GALERY = 22
    }
}