package com.example.todolist.extensions

import com.google.android.material.textfield.TextInputLayout
import java.text.SimpleDateFormat
import java.util.*

// set locale
private val locale = Locale("pt", "br")

// fun format date
fun Date.format() : String {
    return SimpleDateFormat("dd/MM/yyyy", locale).format(this)
}

// var get date
var TextInputLayout.text : String
    get() = editText?.text.toString() ?: ""
    set(value) {
        editText?.setText(value)
    }
