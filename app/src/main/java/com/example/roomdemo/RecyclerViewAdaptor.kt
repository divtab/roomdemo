package com.example.roomdemo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.roomdemo.db.UseEntity

class RecyclerViewAdaptor(val listener: MainActivity): RecyclerView.Adapter<RecyclerViewAdaptor.MyViewHolder>(){
    var items  = ArrayList<UseEntity>()

    fun setListData(data: ArrayList<UseEntity>) {
        this.items = data
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewAdaptor.MyViewHolder {
        val inflater = LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_row, parent, false)
        return MyViewHolder(inflater, listener)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: RecyclerViewAdaptor.MyViewHolder, position: Int) {

        holder.itemView.setOnClickListener {
            listener.onItemClickListener(items[position])
        }
        holder.bind(items[position])

    }



    class MyViewHolder(view: View, val listener: MainActivity): RecyclerView.ViewHolder(view) {


        val tvName = view.findViewById<TextView>(R.id.tvName)
        val tvEmail = view.findViewById<TextView>(R.id.tvEmail)
//        val tvPhone = view.tvPhone
        val deleteUserID = view.findViewById<ImageView>(R.id.deleteUserID)

        fun bind(data: UseEntity) {
            tvName.text = data.name
            tvEmail.text = data.email
//            tvPhone.text = data.phone

            deleteUserID.setOnClickListener {
                listener.onDeleteUserClickListener(data)
            }
        }
    }

    interface RowClickListener{
        fun onDeleteUserClickListener(user: UseEntity)
        fun onItemClickListener(user: UseEntity)
    }
}