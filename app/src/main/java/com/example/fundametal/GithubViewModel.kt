package com.example.fundametal

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.fundametal.berkas.Adapter.ResUser
import com.example.fundametal.berkas.Api.RetrofitApi
import com.example.fundametal.berkas.Model.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.Query

class GithubViewModel : ViewModel(){

    val listUsers = MutableLiveData<ArrayList<User>>()

    fun setSearchUsers(query: String){
        RetrofitApi.apiInstance
            .getSearchUsers(query)
            .enqueue(object : Callback<ResUser>{
                override fun onResponse(call: Call<ResUser>, response: Response<ResUser>) {
                    if(response.isSuccessful){
                        listUsers.postValue(response.body()?.items)
                    }else{
                        Log.d("Fail", "Error: ${response.code()} - ${response.message()}")
                    }
                }

                override fun onFailure(call: Call<ResUser>, t: Throwable) {
                    t.message?.let { Log.d("Fail", it) }
                }

            })
    }

    fun getSearchUser() : LiveData<ArrayList<User>> {
        return  listUsers
    }
    fun getUsersLiveData(): LiveData<ArrayList<User>> {
        return  listUsers
    }
}

