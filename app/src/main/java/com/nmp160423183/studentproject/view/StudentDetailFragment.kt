package com.nmp160423183.studentproject.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStore
import com.nmp160423183.studentproject.R
import com.nmp160423183.studentproject.databinding.FragmentStudentDetailBinding
import com.nmp160423183.studentproject.viewmodel.DetailViewModel
import com.nmp160423183.studentproject.viewmodel.StudentListViewModel

class StudentDetailFragment : Fragment() {
    private lateinit var viewModel: DetailViewModel
    private lateinit var binding: FragmentStudentDetailBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStudentDetailBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //get student from argument
        val student = StudentDetailFragmentArgs.fromBundle(requireArguments()).student

        //instantiate viewModel
        viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
        viewModel.fetch(student)

        observeViewModel()
    }

    fun observeViewModel(){
        viewModel.studentLD.observe(viewLifecycleOwner, Observer{
            binding.txtName.setText(it.name)
            binding.txtNrp.setText(it.id)
            binding.txtBod.setText(it.bod)
            binding.txtPhone.setText(it.phone)
        })
    }

}