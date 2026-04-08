package com.nmp160423183.studentproject.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.nmp160423183.studentproject.R
import com.nmp160423183.studentproject.databinding.FragmentStudentListBinding
import com.nmp160423183.studentproject.viewmodel.StudentListViewModel

class StudentListFragment : Fragment() {
    private lateinit var binding: FragmentStudentListBinding
    private val adapter = StudentListAdapter(arrayListOf())
    private lateinit var viewModel: StudentListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStudentListBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(StudentListViewModel::class.java)
        viewModel.refresh()

        //recycleView instantiate
        binding.recViewStudent.layoutManager = LinearLayoutManager(context)
        binding.recViewStudent.adapter = adapter

        observeViewModel()

        binding.swipeRefresh.setOnRefreshListener {
            viewModel.refresh()
        }
    }

    fun observeViewModel(){
        //observe student LD
        viewModel.studentsLD.observe(viewLifecycleOwner, Observer{
            adapter.updateStudentList(it)
            binding.swipeRefresh.isRefreshing = false
        })
        //observe error LD
        viewModel.studentLoadErrorLD.observe(viewLifecycleOwner, Observer{
            if(it) binding.txtError.visibility = View.VISIBLE
            else binding.txtError.visibility = View.GONE
        })
        //observe loading LD
        viewModel.loadingLD.observe(viewLifecycleOwner, Observer{
            if(it == true) {
                binding.recViewStudent.visibility = View.GONE
                binding.progressLoad.visibility = View.VISIBLE
            } else {
                binding.recViewStudent.visibility = View.VISIBLE
                binding.progressLoad.visibility = View.GONE
            }
        })
    }

}