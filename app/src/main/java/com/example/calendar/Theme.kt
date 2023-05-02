package com.example.calendar

object Theme {
    var currentTheme = R.style.Theme_Calendar

    private const val ACTUAL = R.style.Theme_Calendar
    private const val NEW = R.style.SecondTheme

    fun switchTheme() {
        Theme.currentTheme = when (Theme.currentTheme) {
            ACTUAL -> NEW
            NEW -> ACTUAL
            else -> -1
        }
    }
}