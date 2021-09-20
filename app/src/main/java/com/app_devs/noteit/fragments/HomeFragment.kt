package com.app_devs.noteit.fragments

import android.os.Bundle
import android.view.*
import android.widget.SearchView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.app_devs.noteit.R
import com.app_devs.noteit.adapters.NotesAdapter
import com.app_devs.noteit.databinding.FragmentHomeBinding
import com.app_devs.noteit.model.Notes
import com.app_devs.noteit.viewmodel.NotesViewModel
import com.google.android.material.bottomsheet.BottomSheetDialog

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private val viewModel: NotesViewModel by viewModels()
    private var oldListFiltering= arrayListOf<Notes>()
    private lateinit var adapter:NotesAdapter
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)

        binding.fabAddNotes.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_homeFragment_to_createNoteFragment)
        }

        val staggeredGridLayoutManager=StaggeredGridLayoutManager(2,LinearLayoutManager.VERTICAL)
        binding.rvNotes.layoutManager = staggeredGridLayoutManager

        viewModel.getAllNotes().observe(viewLifecycleOwner, Observer {
            oldListFiltering=it as ArrayList<Notes>
            adapter=NotesAdapter(requireContext(), it)
            binding.rvNotes.adapter = adapter
        })
        binding.filterHigh.setOnClickListener {
            viewModel.getHighNotes().observe(viewLifecycleOwner, Observer {
                oldListFiltering=it as ArrayList<Notes>
                adapter=NotesAdapter(requireContext(), it)
                binding.rvNotes.adapter = adapter
            })
        }
        binding.filterMedium.setOnClickListener {
            viewModel.getMediumNotes().observe(viewLifecycleOwner, Observer {
                oldListFiltering=it as ArrayList<Notes>
                adapter=NotesAdapter(requireContext(), it)
                binding.rvNotes.adapter = adapter
            })
        }
        binding.filterLow.setOnClickListener {
            viewModel.getLowNotes().observe(viewLifecycleOwner, Observer {
                oldListFiltering=it as ArrayList<Notes>
                adapter=NotesAdapter(requireContext(), it)
                binding.rvNotes.adapter = adapter
            })
        }
        binding.allNotesFilter.setOnClickListener {
            viewModel.getAllNotes().observe(viewLifecycleOwner, Observer {
                oldListFiltering=it as ArrayList<Notes>
                adapter=NotesAdapter(requireContext(), it)
                binding.rvNotes.adapter = adapter
            })
        }
        setHasOptionsMenu(true)
        return binding.root
    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu, menu)
        val item=menu.findItem(R.id.search)
        val searchView= item.actionView as SearchView
        searchView.queryHint="Enter notes' title"
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                notesFiltering(newText)
                return true
            }
        })
        super.onCreateOptionsMenu(menu, inflater)
    }

    private fun notesFiltering(newText: String?) {
        val newFilteringList= arrayListOf<Notes>()
        for(i in oldListFiltering){
            if(i.title.contains(newText!!) ||i.subtitle.contains(newText!!))
                newFilteringList.add(i)
        }
        adapter.filtering(newFilteringList)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.delete) {
            deleteAllRecords()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteAllRecords() {
        val bottomSheetDialog= BottomSheetDialog(requireContext(),R.style.BottomSheetStyle)
        bottomSheetDialog.setContentView(R.layout.dialog_delete)
        val textViewYes=bottomSheetDialog.findViewById<TextView>(R.id.dialog_yes)
        val textViewNo=bottomSheetDialog.findViewById<TextView>(R.id.dialog_no)
        val titleDialog=bottomSheetDialog.findViewById<TextView>(R.id.titleDialog)
        titleDialog?.text="Are you sure you want to delete all records?"
        textViewYes?.setOnClickListener {
            viewModel.deleteAllNotes()
            Toast.makeText(requireContext(),"All notes deleted successfully", Toast.LENGTH_LONG).show()
            bottomSheetDialog.dismiss()
        }
        textViewNo?.setOnClickListener {
            bottomSheetDialog.dismiss()
        }
        bottomSheetDialog.show()

    }

}
