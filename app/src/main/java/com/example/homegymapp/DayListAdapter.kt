package com.example.homegymapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.homegymapp.databinding.ActivityDayListBinding
import com.example.homegymapp.databinding.DaylistBinding

class DayListAdapter(val listModel: ArrayList<DayListModel>) : RecyclerView.Adapter<DayListAdapter.MyViewHolder>() {

    class MyViewHolder(val binding: DaylistBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = DaylistBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return listModel.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val dayListModel = listModel[position]
        holder.binding.day.text = dayListModel.day
        holder.binding.des.text = dayListModel.describe
    }
}