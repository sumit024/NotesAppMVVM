package com.app_devs.noteit.fragments

import android.content.Context
import android.os.Bundle
import android.text.format.DateFormat
import android.util.Log
import android.view.*
import android.widget.SearchView
import android.widget.TextView
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.app_devs.noteit.Constants
import com.app_devs.noteit.R
import com.app_devs.noteit.databinding.FragmentEditNoteBinding
import com.app_devs.noteit.model.Notes
import com.app_devs.noteit.viewmodel.NotesViewModel
import com.google.android.material.bottomsheet.BottomSheetDialog
import java.util.*

class EditNoteFragment : Fragment() {
    private val old_notes by navArgs<EditNoteFragmentArgs>()
    private lateinit var binding: FragmentEditNoteBinding
    private var priority:String="1"
    private val viewModel: NotesViewModel by viewModels()
    private var isRecordDeleted=false
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
        setHasOptionsMenu(true)
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
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.delete) {
            deleteRecord()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteRecord() {
        val bottomSheetDialog= BottomSheetDialog(requireContext(),R.style.BottomSheetStyle)
        bottomSheetDialog.setContentView(R.layout.dialog_delete)
        val textViewYes=bottomSheetDialog.findViewById<TextView>(R.id.dialog_yes)
        val textViewNo=bottomSheetDialog.findViewById<TextView>(R.id.dialog_no)
        textViewYes?.setOnClickListener {
            viewModel.deleteNote(old_notes.data.id)
            Toast.makeText(requireContext(),"Note deleted successfully", Toast.LENGTH_SHORT).show()
            bottomSheetDialog.dismiss()
            findNavController().navigate(R.id.action_editNoteFragment_to_homeFragment)
        }
        textViewNo?.setOnClickListener {
            bottomSheetDialog.dismiss()
        }
        bottomSheetDialog.show()

    }

}