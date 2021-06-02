package com.karas.movies.db.utils

object DbContract {
    const val DB_NAME = "movie_database"
    object MovieData {
        const val TABLE_NAME = "movie_entity"
        const val COLUMN_ID = "id"
        const val COLUMN_TITLE = "title"
        const val COLUMN_DESCRIPTION = "description"
        const val COLUMN_RANK = "rank"
        const val COLUMN_ICON_PATH = "icon_path"
        const val COLUMN_RELEASE_DATE = "release_date"
        const val COLUMN_IS_LIKED = "is_liked"
    }
}