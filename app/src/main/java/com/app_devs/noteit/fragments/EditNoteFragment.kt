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
import androidx.navigation.fragment.navArgs
import com.app_devs.noteit.Constants
import com.app_devs.noteit.R
import com.app_devs.noteit.databinding.FragmentEditNoteBinding
import com.app_devs.noteit.model.Notes
import com.app_devs.noteit.viewmodel.NotesViewModel
import java.util.*

class EditNoteFragment : Fragment() {
    private val old_notes by navArgs<EditNoteFragmentArgs>()
    private lateinit var binding: FragmentEditNoteBinding
    private var priority:String="1"
    private val viewModel: NotesViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentEditNoteBinding.inflate(layoutInflater,container,false)
        binding.etEditedTitle.setText(old_notes.data.title)
        binding.etEditedSubtitle.setText(old_notes.data.subtitle)
        binding.etEditedNote.setText(old_notes.data.notes)
        when(old_notes.data.priority){
            "1"->{
                priority="1"
                binding.priorityGreen.setImageResource(R.drawable.ic_done)
                binding.priorityYellow.setImageResource(0)
                binding.priorityRed.setImageResource(0)
            }
            "2"->{
                priority="2"
                binding.priorityYellow.setImageResource(R.drawable.ic_done)
                binding.priorityGreen.setImageResource(0)
                binding.priorityRed.setImageResource(0)
            }
            "3"->{
                priority="3"
                binding.priorityRed.setImageResource(R.drawable.ic_done)
                binding.priorityYellow.setImageResource(0)
                binding.priorityGreen.setImageResource(0)
            }
        }
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
        binding.btnSaveEditedNote.setOnClickListener {
            updateNotes(it)
        }
        return binding.root
    }

    private fun updateNotes(it: View?) {
        val title=binding.etEditedTitle.text.toString()
        val subtitle=binding.etEditedSubtitle.text.toString()
        val notes=binding.etEditedNote.text.toString()
        val d= Date()
        val notesDate:CharSequence= DateFormat.format("MMMM d, yyyy",d.time)
        if(Constants.verifyInput(title,subtitle,notes))
        {
            val notesObj: Notes = Notes(old_notes.data.id,title,subtitle,notes,notesDate.toString(),priority)
            viewModel.updateNote(notesObj)
            Toast.makeText(requireContext(),"Notes updated successfully", Toast.LENGTH_LONG).show()
            Log.d("SUMIT",notesObj.toString())
            Navigation.findNavController(requireView()).navigate(R.id.action_editNoteFragment_to_homeFragment)
        }
        else
        {
            Toast.makeText(requireContext(),"All fields must be filled", Toast.LENGTH_LONG).show()
        }
    }
}