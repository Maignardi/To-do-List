package com.example.todolista.fragments.progressBar

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todolista.R
import com.example.todolista.fragments.list.ListAdapter
import com.example.todolista.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.fragment_list.view.*
import kotlinx.android.synthetic.main.fragment_progress_bar.view.*

class ProgressBar : Fragment() {
    private lateinit var mUserViewModel: UserViewModel



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_progress_bar, container, false)




        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        view.button_entrar.setOnClickListener{
            findNavController().navigate(R.id.action_progressBar_to_listFragment)
        }
        return view
    }
}