package com.app_devs.noteit.fragments

import android.os.Bundle
import android.text.format.DateFormat
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.app_devs.noteit.Constants
import com.app_devs.noteit.R
import com.app_devs.noteit.databinding.FragmentCreateNoteBinding
import com.app_devs.noteit.model.Notes
import com.app_devs.noteit.viewmodel.NotesViewModel
import java.util.*

class CreateNoteFragment : Fragment() {
    private lateinit var binding: FragmentCreateNoteBinding
    private var priority:String="1"
    private val viewModel: NotesViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentCreateNoteBinding.inflate(layoutInflater,container,false)
        binding.btnSaveNote.setOnClickListener { 
            createNotes(it)
        }
        binding.priorityGreen.setImageResource(R.drawable.ic_done)

        binding.priorityGreen.setOnClickListener {
            priority="1"
            binding.priorityGreen.setImageResource(R.drawable.ic_done)
            binding.priorityYellow.setImageResource(0)
            binding.priorityRed.setImageResource(0)
        }
        binding.priorityYellow.setOnClickListener {
            priority="2"
            binding.priorityYellow.setImageResource(R.drawable.ic_done)
            binding.priorityGreen.setImageResource(0)
            binding.priorityRed.setImageResource(0)
        }
        binding.priorityRed.setOnClickListener {
            priority="3"
            binding.priorityRed.setImageResource(R.drawable.ic_done)
            binding.priorityYellow.setImageResource(0)
            binding.priorityGreen.setImageResource(0)
        }
        return binding.root
    }

    private fun createNotes(view: View?) {
        val title=binding.etTitle.text.toString()
        val subtitle=binding.etSubtitle.text.toString()
        val notes=binding.etNote.text.toString()
        val d=Date()
        val notesDate:CharSequence= DateFormat.format("MMMM d, yyyy",d.time)
        if(Constants.verifyInput(title,subtitle,notes))
        {
            val notesObj:Notes=Notes(0,title,subtitle,notes,notesDate.toString(),priority)
            viewModel.addNotes(notesObj)
            Toast.makeText(requireContext(),"Notes created successfully",Toast.LENGTH_LONG).show()
            Log.d("SUMIT",notesObj.toString())
            Navigation.findNavController(requireView()).navigate(R.id.action_createNoteFragment_to_homeFragment)
        }
        else
        {
            Toast.makeText(requireContext(),"All fields must be filled",Toast.LENGTH_LONG).show()
        }

    }

}