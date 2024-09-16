package com.example.homegymapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.homegymapp.databinding.ExerciseBinding

class ExerciseAdapter (val list: ArrayList<ExerciseData>) : RecyclerView.Adapter<ExerciseAdapter.MyViewHolder>()  {

    class MyViewHolder (var binding: ExerciseBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ExerciseBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val exerciseData = list[position]
        holder.binding.exerciseImage.setImageResource(exerciseData.image)
        holder.binding.des.text = exerciseData.title

    }

}