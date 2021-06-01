package com.karas.movies.db.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.karas.movies.db.utils.DbContract

@Entity(tableName = DbContract.MovieData.TABLE_NAME)
data class MovieEntity(@ColumnInfo(name = DbContract.MovieData.COLUMN_ID) @PrimaryKey(autoGenerate = true) val id: Int? = null, val title: String, val description: String, val rank: Float, val imagePath: String)
