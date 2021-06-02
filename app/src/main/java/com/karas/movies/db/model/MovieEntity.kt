package com.karas.movies.db.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.karas.movies.db.utils.DbContract

@Entity(tableName = DbContract.MovieData.TABLE_NAME)
data class MovieEntity(@ColumnInfo(name = DbContract.MovieData.COLUMN_ID) @PrimaryKey(autoGenerate = true) val id: Int? = null,
                       @ColumnInfo(name = DbContract.MovieData.COLUMN_TITLE) val title: String,
                       @ColumnInfo(name = DbContract.MovieData.COLUMN_DESCRIPTION)  val description: String,
                       @ColumnInfo(name = DbContract.MovieData.COLUMN_RANK) val rank: Float,
                       @ColumnInfo(name = DbContract.MovieData.COLUMN_ICON_PATH) val imagePath: String,
                       @ColumnInfo(name = DbContract.MovieData.COLUMN_RELEASE_DATE) val releaseDate: String,
                       @ColumnInfo(name = DbContract.MovieData.COLUMN_IS_LIKED) val isLiked: Boolean)
