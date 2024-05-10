package com.example.check1

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TareaAdapter(private val listaTareas: List<Tarea>) : RecyclerView.Adapter<TareaAdapter.TareaViewHolder>() {

    inner class TareaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nombreTextView: TextView = itemView.findViewById(R.id.nombreTextView)
        val activaTextView: TextView = itemView.findViewById(R.id.activaTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TareaViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_tarea, parent, false)
        return TareaViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: TareaViewHolder, position: Int) {
        val currentTarea = listaTareas[position]
        holder.nombreTextView.text = currentTarea.nombre
        holder.activaTextView.text = if (currentTarea.activa) "Activa" else "Inactiva"
    }

    override fun getItemCount(): Int {
        return listaTareas.size
    }
}