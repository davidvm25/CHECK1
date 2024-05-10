package com.example.check1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.content.Intent
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.LinearLayoutManager



class MainActivity : AppCompatActivity() {

    private lateinit var databaseHelper: DatabaseHelper
    private lateinit var tareaAdapter: TareaAdapter
    private lateinit var listaTareas: RecyclerView
    private lateinit var crearTareaButton: Button

    private var listaTareasData = mutableListOf<Tarea>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        listaTareas = findViewById(R.id.listaTareas)
        crearTareaButton = findViewById(R.id.crearTarea)

        databaseHelper = DatabaseHelper(this)

        // Configurar RecyclerView
        listaTareas.layoutManager = LinearLayoutManager(this)
        tareaAdapter = TareaAdapter(listaTareasData)
        listaTareas.adapter = tareaAdapter

        // Abrir actividad para crear nueva tarea al hacer clic en el botón
        crearTareaButton.setOnClickListener {
            val intent = Intent(this, CrearTareaActivity::class.java)
            startActivity(intent)
        }

        // Obtener todas las tareas almacenadas en la base de datos
        val tareasAlmacenadas = databaseHelper.obtenerTodasLasTareas()

        // Limpiar la lista actual de tareas y agregar las tareas almacenadas
        listaTareasData.clear()
        listaTareasData.addAll(tareasAlmacenadas)

        // Notificar al adaptador que los datos han cambiado
        tareaAdapter.notifyDataSetChanged()
    }

}