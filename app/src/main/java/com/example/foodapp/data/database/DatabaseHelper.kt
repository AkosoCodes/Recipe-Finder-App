package com.example.foodapp.data.database

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.foodapp.utils.Constants
import android.content.Context
import com.example.foodapp.models.Recipe

class DatabaseHelper(context: Context): SQLiteOpenHelper(
    context,
    Constants.DATABASE_NAME,
    null,
    1
) {

    fun checkIsFavorite(id: Int): Boolean {
        val db = this.readableDatabase
        val query = "SELECT * FROM favorites WHERE id = $id"
        val cursor = db.rawQuery(query, null)
        val count = cursor.count
        cursor.close()
        return count > 0
    }

    fun addFavorite(id: Int, title: String, summary: String, image: String) {
        val db = this.writableDatabase

        val cv = ContentValues()
        cv.put("id", id)
        cv.put("title", title)
        cv.put("summary", summary)
        cv.put("image", image)

        db.insert("favorites", null, cv)

    }

    fun removeFavorite(id: Int) {
        val db = this.writableDatabase
        val query = "DELETE FROM favorites WHERE id = $id"
        db.execSQL(query)
    }

    fun getFavorites(): ArrayList<Recipe> {
        val db = this.readableDatabase
        val query = "SELECT * FROM favorites"
        val cursor = db.rawQuery(query, null)
        val favorites = ArrayList<Recipe>()

        while(cursor.moveToNext()) {
            val id = cursor.getInt(0)
            val title = cursor.getString(1)
            val summary = cursor.getString(2)
            val image = cursor.getString(3)


            favorites.add(Recipe(id, title, summary, image))
        }

        cursor.close()
        return favorites
    }

    override fun onCreate(p0: SQLiteDatabase?) {

        val createFavoritesTable = "CREATE TABLE favorites (" +
                "id INTEGER PRIMARY KEY," +
                "title TEXT," +
                "summary TEXT," +
                "image TEXT" +
                ")"

        p0?.execSQL(createFavoritesTable)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }
}