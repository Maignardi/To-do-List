package com.example.todolista.fragments.add

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.todolista.R
import com.example.todolista.model.User
import com.example.todolista.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.fragment_add.*
import kotlinx.android.synthetic.main.fragment_add.view.*


class AddFragment : Fragment() {

    private lateinit var mUserViewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add, container, false)

        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        view.add_btn.setOnClickListener{
            insertDataToDatabase()
        }
        return view
    }

    private fun insertDataToDatabase() {
        val title = addTitle.text.toString()
        val desc = addDesc.text.toString()
        val date = addData.text

        if(inputCheck(title, desc, date)){
            val user = User(
                0,
                title,
                desc,
                Integer.parseInt(date.toString())
            )

            mUserViewModel.addUser(user)
            Toast.makeText(requireContext(),"Adicionado", Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_addFragment_to_listFragment)
        }else{
            Toast.makeText(requireContext(),"Falha, escreva em todos os campos", Toast.LENGTH_LONG).show()

        }
    }

    private fun inputCheck(title: String, desc: String, date: Editable): Boolean{
        return !(TextUtils.isEmpty(title) && TextUtils.isEmpty(desc)  &&  TextUtils.isEmpty(date))
    }
}