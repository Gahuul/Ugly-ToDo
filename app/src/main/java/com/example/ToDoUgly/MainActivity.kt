package com.example.ToDoUgly

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.ToDoUgly.database.AppDatabase
import com.example.ToDoUgly.databinding.ActivityMainBinding
import com.example.ToDoUgly.entities.TaskLine
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import android.widget.ListView

class MainActivity : AppCompatActivity() {
    private lateinit var appDb: AppDatabase
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        appDb = AppDatabase.getDatabase(this)
        binding.btnSave.setOnClickListener {
            writeData()
        }

        binding.btnView.setOnClickListener {
            readData()
        }

        binding.btnDeleteAll.setOnClickListener {
            GlobalScope.launch {
                appDb.TaskDAO().deleteAll()
            }
        }
    }

    private fun writeData() {
        val task = binding.etTask.text.toString()
        
        if (task.isNotEmpty()
        ) {
            val student = TaskLine(null, task)
            GlobalScope.launch(Dispatchers.IO){
                appDb.TaskDAO().insert(student)
            }

            binding.etTask.text.clear()

            Toast.makeText(this@MainActivity, "Successfully written", Toast.LENGTH_LONG).show()
        }
    }

    private fun readData(){

            GlobalScope.launch{
                val tasks = arrayOf(appDb.TaskDAO().getAll())
                val arrayAdapter: ArrayAdapter<*>
                withContext(Dispatchers.Main){
                    var mListView = findViewById<ListView>(R.id.userlist)
                    arrayAdapter = ArrayAdapter(this@MainActivity,
                        android.R.layout.simple_list_item_1, tasks)
                    mListView.adapter = arrayAdapter

            }
        }
    }
}


