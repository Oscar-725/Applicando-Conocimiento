package com.example.applicandoconocimiento.ui.view

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.applicandoconocimiento.R
import com.example.applicandoconocimiento.adapter.LessonAdapter
import com.example.applicandoconocimiento.data.database.entities.Lesson
import com.example.applicandoconocimiento.databinding.FragmentShowLessonBinding
import com.example.applicandoconocimiento.ui.viewmodel.ShowLessonActions
import com.example.applicandoconocimiento.ui.viewmodel.ShowLessonViewModel

class ShowLessonFragment : Fragment() {

    private val binding: FragmentShowLessonBinding by lazy {
        FragmentShowLessonBinding.inflate(layoutInflater)
    }

    lateinit var lessonViewModel: ShowLessonViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = binding.root


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lessonViewModel = ViewModelProvider(this)[ShowLessonViewModel::class.java]
        bindActionViewModel()

        binding.addLesson.setOnClickListener {
            findNavController().navigate(R.id.action_showLesson_to_addLesson)
        }

        //initRecyclerView()

    }

    override fun onResume() {
        super.onResume()
        lessonViewModel.getLessonTitles()
    }

   /* private fun initRecyclerView() {
        val recyclerView = binding.rvShowLesson
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = LessonAdapter()

    } */

    private fun bindActionViewModel() {
        lessonViewModel.getActionLiveData().observe(viewLifecycleOwner, this::handleAction)
    }

    private fun handleAction(actions: ShowLessonActions) {
        when (actions) {
            is ShowLessonActions.ShowLesson -> chargeLesson(actions.result)
            is ShowLessonActions.ShowTitles -> setTitleList(actions.result)
            is ShowLessonActions.ShowLastData -> Log.e("ERROR","ERROR")
            is ShowLessonActions.InserLesson -> TODO()
            is ShowLessonActions.ErrorLesson -> TODO()
            else -> {}
        }

    }

    private fun setTitleList(list: List<String>) {

        val adapter= ArrayAdapter(requireContext(),android.R.layout.simple_spinner_dropdown_item,list.toMutableList())
        binding.searchLesson.setAdapter(adapter)

        binding.searchLesson.setOnItemClickListener {adapterView, view, i ,l ->
            // view model - querty para setear pantalla
            val title = adapterView.getItemAtPosition(i) as String
            lessonViewModel.showLesson(title)

            Toast.makeText(requireContext(),title,Toast.LENGTH_SHORT).show()
        }

    }

    private fun chargeLesson(lesson: Lesson) {
        val imagen = binding.ivLesson
        val coment = binding.tvComentsLesson
        val bmp: Bitmap = BitmapFactory.decodeByteArray(lesson.imagen,0,lesson.imagen.size)

        coment.text = lesson.comentario
        imagen.setImageBitmap(
            Bitmap.createScaledBitmap(bmp,imagen.width,imagen.height,false)
        )
    }
}

