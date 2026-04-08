package com.nmp160423183.studentproject.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.nmp160423183.studentproject.model.Student

class StudentListViewModel(application: Application) : AndroidViewModel(application) { //pake androidviewmodel agar volley bisa menerima konteks
    val studentsLD = MutableLiveData<ArrayList<Student>>()
    val studentLoadErrorLD = MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()

    val TAG = "volleyTag"
    private var queue: RequestQueue? = null


    fun refresh(){
        loadingLD.value = true 			// progress bar start muncul
        studentLoadErrorLD.value = false  	// tidak ada error
//        studentsLD.value = arrayListOf(
//            Student("16055","Nonie","1998/03/28","5718444778","http://dummyimage.com/75x100" + ".jpg/cc0000/ffffff"),
//            Student("13312","Rich","1994/12/14","3925444073","http://dummyimage.com/75x100" + ".jpg/5fa2dd/ffffff"),
//            Student("11204","Dinny","1994/10/07","6827808747","http://dummyimage.com/75x100.jpg/5fa2dd/ffffff1")
//        )  GA DI PAKE KARENA DIGANTI VOLLEY

        queue = Volley.newRequestQueue(getApplication()) // disini ada hubungan dgn androidviewmodel yang di atas
        val url = "https://www.jsonkeeper.com/b/LLMW"

        val stringRequest = StringRequest(
            Request.Method.GET, url,
            {
                //volley success
                val sType = object : TypeToken<List<Student>>() { }.type
                val result = Gson().fromJson<List<Student>>(it, sType)
                studentsLD.value = result as ArrayList<Student>
                loadingLD.value = false
                Log.d("showvoley", it)

            },
            {
                //volley error
                Log.d("showvoley", it.toString())
                studentLoadErrorLD.value = true
                loadingLD.value = false
            },
        )

        queue?.add(stringRequest)

        studentLoadErrorLD.value = false
        loadingLD.value = false
    }

    override fun onCleared() {
        super.onCleared()
        queue?.cancelAll(TAG)
    }

}