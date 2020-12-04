package com.devkanhaiya.itunessearchprojectkotlin.DataBase

import androidx.lifecycle.LiveData
import androidx.room.*
import retrofit2.http.DELETE

@Dao
interface OffDao {
        @Insert(onConflict = OnConflictStrategy.IGNORE)
        suspend fun insert(offdata: OffData)


        @Query("DELETE FROM OFFLINEDATA")
        suspend fun deleteall()


        @Delete
        suspend fun delete(offdata: OffData)



        @Query("Select * from offlinedata")
        fun getAllNotes(): LiveData<List<OffData>>
}