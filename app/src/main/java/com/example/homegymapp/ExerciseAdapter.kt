package com.example.homegymapp

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.homegymapp.databinding.ExerciseBinding


class ExerciseAdapter(private var exerciseList: ArrayList<ExerciseData>) : RecyclerView.Adapter<ExerciseAdapter.ExerciseViewHolder>() {

    class ExerciseViewHolder(val binding: ExerciseBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExerciseViewHolder {
        val binding = ExerciseBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ExerciseViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return exerciseList.size
    }

    override fun onBindViewHolder(holder: ExerciseViewHolder, position: Int) {
        val exercise = exerciseList[position]
        holder.binding.exercisename.text = exercise.name
        holder.binding.time.text = exercise.time
        holder.binding.exerciselayout.setOnClickListener {
            val intent = Intent(holder.itemView.context, ExerciceDetails::class.java)
            intent.putExtra("name", exercise.name)
            intent.putExtra("time", exercise.time)
            intent.putExtra("video", exercise.videoUrl)
            holder.itemView.context.startActivity(intent)
        }

    }


}
