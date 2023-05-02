package com.example.calendar

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.calendar.databinding.ActivityScheduleBinding
import kotlin.random.Random

class Schedule : AppCompatActivity() {

    private lateinit var databind: ActivityScheduleBinding
    private lateinit var event: Event

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        databind = ActivityScheduleBinding.inflate(layoutInflater)
        setContentView(databind.root)

        val day = intent.getIntExtra("day",0)
        val month = intent.getIntExtra("month",0)
        val year = intent.getIntExtra("year",0)
        val date = "$day/$month/$year"
        databind.textView3.text = "** $date **"

        databind.buttonSave.setOnClickListener {
            Intent().apply {
                fun saveEvent(): Event {
                    return Event("$date\n${databind.editText.text}")
                }
                event = saveEvent()
                putExtra("EVENT", event)
                setResult(RESULT_OK, this)
            }
            this.finish()
        }
    }
}