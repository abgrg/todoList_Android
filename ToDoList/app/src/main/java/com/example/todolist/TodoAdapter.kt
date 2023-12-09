package com.example.todolist

import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TodoAdapter(
    private val toDos: MutableList<Todo>
) : RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {

    class TodoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        return TodoViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_todo,
                parent,
                false
            )
        )
    }


    fun addTodo(todo : Todo){
        toDos.add(todo)
        notifyItemInserted(toDos.size-1)
    }

    fun delTodo(){
        toDos.removeAll {
            it.isChecked
        }
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return toDos.size
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        var curTodo = toDos[position]
        holder.itemView.findViewById<TextView>(R.id.tvTodoTitle).text = curTodo.title
        holder.itemView.findViewById<CheckBox>(R.id.cbDone).isChecked = curTodo.isChecked
        toggleStrikeThrough(holder.itemView.findViewById<TextView>(R.id.tvTodoTitle), curTodo.isChecked)
        holder.itemView.findViewById<CheckBox>(R.id.cbDone).setOnCheckedChangeListener { _, isChecked ->
            toggleStrikeThrough(holder.itemView.findViewById<TextView>(R.id.tvTodoTitle),isChecked)
            curTodo.isChecked = !curTodo.isChecked
        }
    }

    private fun toggleStrikeThrough(tvTodoTitle : TextView, isChecked : Boolean){
        if(isChecked){
            tvTodoTitle.paintFlags = tvTodoTitle.paintFlags or STRIKE_THRU_TEXT_FLAG
        }else{
            tvTodoTitle.paintFlags = tvTodoTitle.paintFlags and STRIKE_THRU_TEXT_FLAG.inv()
        }
    }
}