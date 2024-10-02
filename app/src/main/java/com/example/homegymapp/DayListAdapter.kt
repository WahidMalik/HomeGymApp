package com.example.homegymapp

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.homegymapp.databinding.DaylistBinding

class DayListAdapter(private val listModel: ArrayList<DayListModel>) : RecyclerView.Adapter<DayListAdapter.MyViewHolder>() {

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
        holder.binding.dayslist.setOnClickListener {
            val intent = Intent(holder.itemView.context, Exercices::class.java)
            intent.putExtra("dayId", dayListModel.dayId) // Ensure dayListModel has id property
            holder.itemView.context.startActivity(intent)
        }
    }
}
