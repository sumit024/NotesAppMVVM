package com.app_devs.noteit

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.app_devs.noteit.databinding.FragmentCreateNoteBinding

class CreateNoteFragment : Fragment() {
    private lateinit var binding: FragmentCreateNoteBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentCreateNoteBinding.inflate(layoutInflater,container,false)
        return binding.root
    }
}