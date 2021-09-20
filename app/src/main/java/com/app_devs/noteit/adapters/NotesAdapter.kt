package com.app_devs.noteit.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.app_devs.noteit.R
import com.app_devs.noteit.databinding.ItemNotesBinding
import com.app_devs.noteit.fragments.HomeFragment
import com.app_devs.noteit.fragments.HomeFragmentDirections
import com.app_devs.noteit.model.Notes

class NotesAdapter(val context: Context,var list:List<Notes>):RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    fun filtering(newFilteredList:ArrayList<Notes>)
    {
        list=newFilteredList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MyViewHolder(ItemNotesBinding.inflate(LayoutInflater.from(context),parent,false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val model=list[position]
       if(holder is MyViewHolder)
       {
           holder.binding.notesTitle.text=model.title
           holder.binding.notesSubtitle.text=model.subtitle
           holder.binding.notesDate.text=model.date
           when(model.priority){
               "1"->{
                   holder.binding.notePriority.setBackgroundResource(R.drawable.green_dot)
               }
               "2"->{
                   holder.binding.notePriority.setBackgroundResource(R.drawable.yellow_dot)
               }
               "3"->{
                   holder.binding.notePriority.setBackgroundResource(R.drawable.red_dot)
               }
           }
           holder.itemView.setOnClickListener {
               val action=HomeFragmentDirections.actionHomeFragmentToEditNoteFragment(model)
               Navigation.findNavController(it).navigate(action)
           }

       }
    }

    override fun getItemCount(): Int {
        return list.size

    }
    class MyViewHolder(val binding: ItemNotesBinding):RecyclerView.ViewHolder(binding.root)
}