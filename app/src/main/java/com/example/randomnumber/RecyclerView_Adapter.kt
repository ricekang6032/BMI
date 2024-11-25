package com.example.randomnumber

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerView_Adapter(private val dataList: List<RecyclerView_Data>): RecyclerView.Adapter<RecyclerView_Adapter.mViewHolder>(){

    // ViewHolder 必須是 inner class
    inner class mViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val detail: TextView = itemView.findViewById(R.id.list_textView)
        val idTextView: TextView = itemView.findViewById(R.id.id_textView)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): mViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_menu, parent, false)
        return mViewHolder(view)
    }

    override fun onBindViewHolder(holder: mViewHolder, position: Int) {
        holder.apply {
            detail.text = dataList[position].bmi
            idTextView.text = "${dataList[position].id} BMI紀錄："
        }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }
}