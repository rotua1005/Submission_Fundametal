package com.example.fundametal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fundametal.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private  lateinit var viewModel: GithubViewModel
    private  lateinit var adapter : ListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = ListAdapter()
        adapter.notifyDataSetChanged()
        viewModel = ViewModelProvider(this,ViewModelProvider.NewInstanceFactory()).get(GithubViewModel::class.java)


        binding.apply {
            ryUser.layoutManager = LinearLayoutManager(this@MainActivity)
            ryUser.setHasFixedSize(true)
            ryUser.adapter = adapter


            viewModel.setSearchUsers("q")

            btnSearch.setOnClickListener {
                searchUser()
            }

            editQuery.setOnKeyListener { view, i, keyEvent ->
                if (keyEvent.action == KeyEvent.ACTION_DOWN && i == KeyEvent.KEYCODE_ENTER) {
                    searchUser()
                    return@setOnKeyListener true
                }
                return@setOnKeyListener false
            }
        }

        viewModel.getSearchUser().observe(this, {
            if (it != null) {
                adapter.setList(it)
                showLoading(false)
            }
        })


        viewModel.getUsersLiveData().observe(this, { users ->
            if (users != null) {
                adapter.setList(users)
                showLoading(false)
            }
        })
    }
            private fun searchUser(){
                binding.apply {
                    val query = editQuery.text.toString()
                    if (query.isEmpty())return
                    showLoading(true)
                    viewModel.setSearchUsers(query)
                }
            }

            private fun showLoading(state : Boolean){
                if(state){
                    binding.proses.visibility = View.VISIBLE
                }else{
                    binding.proses.visibility = View.GONE
                }
            }
    }
