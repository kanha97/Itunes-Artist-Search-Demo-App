package com.devkanhaiya.itunessearchprojectkotlin

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.devkanhaiya.itunessearchprojectkotlin.DataBase.OffData
import com.devkanhaiya.itunessearchprojectkotlin.DataBase.OffDataBase
import com.devkanhaiya.itunessearchprojectkotlin.Repository.ItunesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ItunesModel(application: Application) : AndroidViewModel(application){


    private val repository : ItunesRepository

    val allData: LiveData<List<OffData>>


    init {
        val dao = OffDataBase.getDatabase(application).getNoteDao()

        repository =  ItunesRepository(dao)

        allData = repository.allDatas
    }

    fun deleteData() = viewModelScope.launch(Dispatchers.IO) {
        repository.deleteall()
    }

    fun insertData(data: OffData) = viewModelScope.launch(Dispatchers.IO){
        repository.insert(data)
    }

}