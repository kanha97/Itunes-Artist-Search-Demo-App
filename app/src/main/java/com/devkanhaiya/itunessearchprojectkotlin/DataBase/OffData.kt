package com.devkanhaiya.itunessearchprojectkotlin.DataBase

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "offlinedata")
class OffData(@PrimaryKey@ColumnInfo( name = "nameTrack",)val nameTrack : String,
              @ColumnInfo(name="image")val image:String,
              @ColumnInfo(name="previewsUrl")val previewsUrl:String,

              )