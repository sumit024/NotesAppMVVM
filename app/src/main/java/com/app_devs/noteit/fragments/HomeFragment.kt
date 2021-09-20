package com.app_devs.noteit.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import com.app_devs.noteit.R
import com.app_devs.noteit.adapters.NotesAdapter
import com.app_devs.noteit.databinding.FragmentHomeBinding
import com.app_devs.noteit.viewmodel.NotesViewModel

class HomeFragment : Fragment() {
    private lateinit var binding:FragmentHomeBinding
     private val viewModel:NotesViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentHomeBinding.inflate(layoutInflater,container,false)
        binding.fabAddNotes.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_homeFragment_to_createNoteFragment)
        }
        viewModel.getAllNotes().observe(viewLifecycleOwner, Observer {
            binding.rvNotes.layoutManager=GridLayoutManager(requireContext(),2)
            binding.rvNotes.adapter=NotesAdapter(requireContext(),it)
        })
        binding.filterHigh.setOnClickListener {
            viewModel.getHighNotes().observe(viewLifecycleOwner, Observer {
                binding.rvNotes.layoutManager=GridLayoutManager(requireContext(),2)
                binding.rvNotes.adapter=NotesAdapter(requireContext(),it)
            })
        }
        binding.filterMedium.setOnClickListener {
            viewModel.getMediumNotes().observe(viewLifecycleOwner, Observer {
                binding.rvNotes.layoutManager=GridLayoutManager(requireContext(),2)
                binding.rvNotes.adapter=NotesAdapter(requireContext(),it)
            })
        }
        binding.filterLow.setOnClickListener {
            viewModel.getLowNotes().observe(viewLifecycleOwner, Observer {
                binding.rvNotes.layoutManager=GridLayoutManager(requireContext(),2)
                binding.rvNotes.adapter=NotesAdapter(requireContext(),it)
            })
        }
        binding.allNotesFilter.setOnClickListener {
            viewModel.getAllNotes().observe(viewLifecycleOwner, Observer {
                binding.rvNotes.layoutManager=GridLayoutManager(requireContext(),2)
                binding.rvNotes.adapter=NotesAdapter(requireContext(),it)
            })
        }
        return binding.root
    }
}