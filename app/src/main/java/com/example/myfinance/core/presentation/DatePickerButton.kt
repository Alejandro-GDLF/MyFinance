package com.example.myfinance.core.presentation

import androidx.compose.material3.Button
import android.app.DatePickerDialog
import android.widget.DatePicker
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import java.util.Calendar
import java.util.Date

@Composable
fun DatePickerButton(
    onDatePicked: (Date) -> Unit
) {
    var date by remember { mutableStateOf<Date?>(null) }
    val context = LocalContext.current
    var showDialog by remember { mutableStateOf(false) }
    
    Button(onClick = { showDialog = true }) {
        Text(text = if (date != null ) date!!.toLocaleString() else "Pick a date" )
    }

    if(showDialog) {
        val calendar = Calendar.getInstance()

        DatePickerDialog(
            context,
            {_: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
                date = Calendar.getInstance().apply {
                    set(year, month, dayOfMonth)
                }.time
                onDatePicked(date!!)
                showDialog = false
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        ).show()
    }

}