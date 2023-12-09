package com.example.todolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var todoAdapter : TodoAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        todoAdapter = TodoAdapter(mutableListOf())
        findViewById<RecyclerView>(R.id.rvToDoItems).adapter = todoAdapter
        findViewById<RecyclerView>(R.id.rvToDoItems).layoutManager = LinearLayoutManager(this)

        findViewById<Button>(R.id.btnAddToDo).setOnClickListener {

            val toDoTitle = findViewById<EditText>(R.id.etToDoTile).text.toString()
            if(toDoTitle.isNotEmpty()){
                val todo = Todo(toDoTitle)
                todoAdapter.addTodo(todo)
                findViewById<EditText>(R.id.etToDoTile).text.clear()
            }
        }
        findViewById<Button>(R.id.btnDelToDo).setOnClickListener {
            todoAdapter.delTodo()
        }
    }
}