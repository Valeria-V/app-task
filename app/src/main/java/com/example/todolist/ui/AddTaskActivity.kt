package com.example.todolist.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.todolist.databinding.ActivityAddTaskBinding
import com.example.todolist.datasource.TaskDataSource
import com.example.todolist.extensions.format
import com.example.todolist.extensions.text
import com.example.todolist.model.Task
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import java.util.*

class AddTaskActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddTaskBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAddTaskBinding.inflate(layoutInflater)
        setContentView(binding.root)

        insertListeners()
    }

    // Add date calendar
    private fun insertListeners(){
        binding.tilDate.editText?.setOnClickListener {
            val datePicker = MaterialDatePicker.Builder.datePicker().build()
            datePicker.addOnPositiveButtonClickListener {
                val timeZone = TimeZone.getDefault()
                val offset = timeZone.getOffset(Date().time) * -1
                binding.tilDate.text = Date(it + offset).format()
            }
            datePicker.show(supportFragmentManager, "DATE_PICKER_TAG")
        }

        // Add hour
        binding.tilHour.editText?.setOnClickListener {
            val timePiker = MaterialTimePicker.Builder()
                .setTimeFormat(TimeFormat.CLOCK_24H)
                .build()

            timePiker.addOnPositiveButtonClickListener {
                val minute = if (timePiker.minute in 0..9) "0${timePiker.minute}" else timePiker.minute
                val hour = if (timePiker.hour in 0..9) "0${timePiker.hour}" else timePiker.hour

                binding.tilHour.text = "$hour:$minute"
            }

            timePiker.show(supportFragmentManager, null)
        }

        // Btn Cancel
        binding.btnCancel.setOnClickListener {
            finish()
        }

        // Btn add new task
        binding.btnNewTask.setOnClickListener {
            val task = Task(
                title = binding.tilTitle.text,
                date = binding.tilDate.text,
                hour = binding.tilHour.text
            )
            TaskDataSource.insertTask(task)
        }
    }

}