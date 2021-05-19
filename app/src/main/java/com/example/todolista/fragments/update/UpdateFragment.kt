package com.example.todolista.fragments.update

import android.app.AlertDialog
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.todolista.R
import com.example.todolista.model.User
import com.example.todolista.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.fragment_update.*
import kotlinx.android.synthetic.main.fragment_update.view.*

class UpdateFragment : Fragment() {

    private val args by navArgs<UpdateFragmentArgs>()

    private lateinit var mUserViewModel: UserViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_update, container, false)

        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        view.updateTitle_et.setText(args.currentUser.title)
        view.updateDesc_et.setText(args.currentUser.desc)
        view.updateDate.setText(args.currentUser.date.toString())


        view.update_btn.setOnClickListener {
            updateItem()
        }

        setHasOptionsMenu(true)



        return view
    }

    private fun updateItem(){

        val title = updateTitle_et.text.toString()
        val desc = updateDesc_et.text.toString()
        val date = Integer.parseInt(updateDate.text.toString())

        if(inputCheck(title, desc, updateDate.text )){

            val updateUser = User(args.currentUser.id, title, desc, date)

            mUserViewModel.updateUser(updateUser)
            Toast.makeText(requireContext(), "Troca feita com sucesso", Toast.LENGTH_SHORT).show()

            findNavController().navigate(R.id.action_listFragment_to_updateFragment)

        }else{
            Toast.makeText(requireContext(), "Ocorreu uma falha", Toast.LENGTH_SHORT).show()



        }
    }
    private fun inputCheck(title: String, desc: String, date: Editable): Boolean{
        return !(TextUtils.isEmpty(title) && TextUtils.isEmpty(desc)  &&  TextUtils.isEmpty(date))
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if(item.itemId == R.id.menu_delete){
            deleteUser()
        }
        return super.onOptionsItemSelected(item)
    }
    private fun deleteUser(){
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Sim"){_,_->
            mUserViewModel.deleteUser(args.currentUser)
            Toast.makeText(requireContext(), "Removido com sucesso: ${args.currentUser.title}", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)


        }

        builder.setNegativeButton("Nao"){_, _ -> }
        builder.setTitle("Delete ${args.currentUser.title}?")
        builder.setMessage("Quer mesmo deletar?${args.currentUser.title}?")
        builder.create().show()
    }
}