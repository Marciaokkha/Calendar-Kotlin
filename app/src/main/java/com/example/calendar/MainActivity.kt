package com.example.calendar

import android.app.Activity
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import com.example.calendar.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var databind: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(Theme.currentTheme)
        databind = ActivityMainBinding.inflate(layoutInflater)
        setContentView(databind.root)

        databind.buttonChangeTheme.setOnClickListener {
            Theme.switchTheme()
            recreate()
        }

        databind.calendarView.setOnDateChangeListener {view, year, month, dayOfMonth ->
            val intent = Intent(this, Schedule::class.java)
            intent.putExtra("day", dayOfMonth)
            intent.putExtra("month", month + 1)
            intent.putExtra("year", year)
            register.launch(intent)
        }
    }

    private val register = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result: ActivityResult ->
        if (result.resultCode == Activity.RESULT_OK) {
            result.data?.let { data ->
                val eventExtra = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    data.getParcelableExtra("EVENT", Event::class.java)
                } else {
                    data.getParcelableExtra<Event>("EVENT")
                }
                eventExtra?.let {
                    databind.textEvents.text = eventExtra.event
                }
            }
        }
    }
}