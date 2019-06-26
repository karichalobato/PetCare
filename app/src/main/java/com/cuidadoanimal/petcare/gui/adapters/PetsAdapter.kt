package com.cuidadoanimal.petcare.gui.adapters


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.NavHostFragment

import androidx.recyclerview.widget.RecyclerView
import com.cuidadoanimal.petcare.R
import com.cuidadoanimal.petcare.data.database.entities.Pet
import kotlinx.android.synthetic.main.fragment_list_pet_item.view.*


class PetsAdapter(var items:List<Pet>): RecyclerView.Adapter<PetsAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fragment_list_pet_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    fun updateList(newMatchs: List<Pet>) {
        newMatchs.forEach {
            println("QUe pex")
        }
        this.items = newMatchs
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(item: Pet) = with(itemView) {
       itemView.nombreTextView.text = item.name
           itemView.setOnClickListener {
          val bundle : Bundle? = null
               if (bundle != null) {
                   bundle.putInt("id",item.idPet.toInt())
               }

               findNavController(itemView).navigate(R.id.pet_dest, null
               )
           }
        }
    }
}

