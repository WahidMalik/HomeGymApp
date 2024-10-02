package com.example.homegymapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.homegymapp.databinding.ExerciseBinding


class ExerciseAdapter(private var exerciseList: List<ExerciseDataEntity>) : RecyclerView.Adapter<ExerciseAdapter.ExerciseViewHolder>() {

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
        holder.binding.exercuse.text = exercise.name
        holder.binding.time.text = exercise.time
        holder.binding.exerciseImage.setImageResource(exercise.image)
    }

    fun updateData(newExercises: List<ExerciseDataEntity>) {
        exerciseList = newExercises
        notifyDataSetChanged()
    }
}
