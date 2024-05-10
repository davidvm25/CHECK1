package com.example.check1
import android.util.Log
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class CrearTareaActivity : AppCompatActivity() {

    private lateinit var etNombre: EditText
    private lateinit var etDescripcion: EditText
    private lateinit var etFecha: EditText
    private lateinit var etHora: EditText
    private lateinit var btGuardarTarea: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_tarea)

        etNombre = findViewById(R.id.etNombre)
        etDescripcion = findViewById(R.id.etDescripcion)
        etFecha = findViewById(R.id.etFecha)
        etHora = findViewById(R.id.etHora)
        btGuardarTarea = findViewById(R.id.btGuardarTarea)

        btGuardarTarea.setOnClickListener {
            val nombre = etNombre.text.toString()
            val descripcion = etDescripcion.text.toString()
            val fecha = etFecha.text.toString()
            val hora = etHora.text.toString()

            // Crear la nueva tarea y guardarla
            val nuevaTarea = Tarea(nombre, descripcion, fecha, hora, true) // Supongamos que todas las tareas se crean como activas
            // Aquí debes guardar la nueva tarea en tu base de datos o en la lista de tareas de la actividad principal

            // Añadir lógica para guardar la tarea

            // Finalizar la actividad y regresar a la actividad principal
            finish()
        }
    }
}