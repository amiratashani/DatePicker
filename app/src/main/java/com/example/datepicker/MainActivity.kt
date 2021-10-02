package com.example.datepicker

import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.datepicker.api.PersianPickerDate
import com.example.datepicker.util.PersianHelper
import com.example.datepicker.view.PersianDatePicker
import java.util.*

class MainActivity : AppCompatActivity() {

    lateinit var persianDatePicker: PersianDatePicker
    lateinit var textDate:TextView
    private var titleType = 1
    val NO_TITLE = 0
    val DAY_MONTH_YEAR = 1
    val WEEKDAY_DAY_MONTH_YEAR = 2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        persianDatePicker = findViewById(R.id.datePicker)
        textDate = findViewById(R.id.date_text)

        Toast.makeText(this, persianDatePicker.displayDate.toString(), Toast.LENGTH_SHORT).show()
        Log.d("BADMAN", persianDatePicker.displayDate.time.toString())

        persianDatePicker.displayDate = Date(1631729110000L)
        Log.d("BADMAN", persianDatePicker.displayDate.time.toString())

        persianDatePicker.setOnDateChangedListener { newYear, newMonth, newDay ->
                    updateView(textDate,persianDatePicker.persianDate)
        }





    }

    private fun updateView(dateText: TextView, persianDate: PersianPickerDate) {
        val date: String
        when (titleType) {
            NO_TITLE -> {
                val layoutParams = dateText.layoutParams as LinearLayout.LayoutParams
                layoutParams.setMargins(0, 0, 0, 30)
                dateText.layoutParams = layoutParams
            }
            DAY_MONTH_YEAR -> {
                date = persianDate.persianDay.toString() + " " +
                        persianDate.persianMonth + " " +
                        persianDate.persianYear
                dateText.text = PersianHelper.toPersianNumber(date)
            }
           WEEKDAY_DAY_MONTH_YEAR -> {
                date = persianDate.persianDayOfWeekName.toString() + " " +
                        persianDate.persianDay + " " +
                        persianDate.persianMonthName + " " +
                        persianDate.persianYear
               dateText.text = PersianHelper.toPersianNumber(date)
            }
            else -> Log.d("PersianDatePickerDialog", "never should be here")
        }
    }
}