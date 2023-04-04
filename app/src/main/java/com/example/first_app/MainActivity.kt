package com.example.first_app

import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.floor

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var bornDay: Int = 0
        var bornMonth: Int = 0
        var bornYear: Int = 0

        val btnDatePicker = findViewById<Button>(R.id.Select_Date_Button)
        val dateText = findViewById<TextView>(R.id.date)
        val resultText = findViewById<TextView>(R.id.result)
        val calculateButton = findViewById<Button>(R.id.Calculate_button)

        fun calculateDate() {
            if (bornDay != 0 && bornMonth != 0 && bornYear != 0) {
                val sdf = SimpleDateFormat("dd/MM/yyyy")
                val selectedDate =
                    sdf.parse("${bornDay.toString()}/${bornMonth.toString()}/${bornYear.toString()}").time
                val currentDate = Date().time;
                val diffInDays = floor(((currentDate - selectedDate) / 86400000).toDouble()).toInt()
                resultText.setText("You are ${diffInDays.toString()} days old.")
            }
        }

        fun setDate(year: Int, month: Int, dayOfMonth: Int) {
            Log.d("Date", "${year} ${month} ${dayOfMonth}")
            bornDay = dayOfMonth
            bornMonth = month + 1
            bornYear = year
            dateText.setText("${bornDay}/${bornMonth}/${bornYear}")
        }

        fun onDatePick() {
            val c = Calendar.getInstance()
            val year = c.get(Calendar.YEAR)
            val month = c.get(Calendar.MONTH)
            val day = c.get(Calendar.DAY_OF_MONTH)
            DatePickerDialog(
                this,
                DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                    setDate(
                        year,
                        month,
                        dayOfMonth
                    )
                }, year, month, day
            ).show()
        }

        calculateButton.setOnClickListener {
            calculateDate()
        }

        btnDatePicker.setOnClickListener {
            onDatePick()
        }
    }


}