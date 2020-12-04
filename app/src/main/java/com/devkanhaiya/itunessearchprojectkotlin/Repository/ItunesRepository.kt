package com.devkanhaiya.itunessearchprojectkotlin.Repository

import androidx.lifecycle.LiveData
import com.devkanhaiya.itunessearchprojectkotlin.DataBase.OffData
import com.devkanhaiya.itunessearchprojectkotlin.DataBase.OffDao

class ItunesRepository(private val offdao: OffDao) {


        val allDatas: LiveData<List<OffData>> = offdao.getAllNotes()

        suspend fun insert(note: OffData){
            offdao.insert(note)
        }
        suspend fun deleteall(){
            offdao.deleteall()
        }



    }
