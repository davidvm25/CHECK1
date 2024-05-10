package com.example.check1

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "TareasDB"
        private const val TABLE_TAREAS = "tareas"

        // Columnas de la tabla de tareas
        private const val KEY_ID = "id"
        private const val KEY_NOMBRE = "nombre"
        private const val KEY_DESCRIPCION = "descripcion"
        private const val KEY_FECHA = "fecha"
        private const val KEY_HORA = "hora"
        private const val KEY_ACTIVA = "activa"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_TAREAS_TABLE = ("CREATE TABLE $TABLE_TAREAS($KEY_ID INTEGER PRIMARY KEY, $KEY_NOMBRE TEXT, $KEY_DESCRIPCION TEXT, $KEY_FECHA TEXT, $KEY_HORA TEXT, $KEY_ACTIVA INTEGER)")
        db?.execSQL(CREATE_TAREAS_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        // Aquí puedes manejar las actualizaciones de la base de datos
    }

    // Métodos para CRUD
    fun insertarTarea(tarea: Tarea): Long {
        val db = this.writableDatabase
        val values = ContentValues().apply {
            put(KEY_NOMBRE, tarea.nombre)
            put(KEY_DESCRIPCION, tarea.descripcion)
            put(KEY_FECHA, tarea.fecha)
            put(KEY_HORA, tarea.hora)
            put(KEY_ACTIVA, if (tarea.activa) 1 else 0)
        }
        return db.insert(TABLE_TAREAS, null, values)
    }

    fun obtenerTodasLasTareas(): List<Tarea> {
        val tareas = mutableListOf<Tarea>()
        val selectQuery = "SELECT * FROM $TABLE_TAREAS"
        val db = this.readableDatabase
        val cursor: Cursor? = db.rawQuery(selectQuery, null)
        cursor?.use {
            if (it.moveToFirst()) {
                do {
                    val tarea = Tarea(
                        it.getLong(it.getColumnIndex(KEY_ID)),
                        it.getString(it.getColumnIndex(KEY_NOMBRE)),
                        it.getString(it.getColumnIndex(KEY_DESCRIPCION)),
                        it.getString(it.getColumnIndex(KEY_FECHA)),
                        it.getString(it.getColumnIndex(KEY_HORA)),
                        it.getInt(it.getColumnIndex(KEY_ACTIVA)) == 1
                    )

                    tareas.add(tarea)
                } while (it.moveToNext())
            }
        }
        return tareas
    }
}