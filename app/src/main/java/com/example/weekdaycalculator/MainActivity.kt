package com.example.weekdaycalculator

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.weekdaycalculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.calculateButton.setOnClickListener { calculateDay() }
    }

    private fun calculateDay() {
        val stringInTextField1 = binding.editDateField.text.toString()
        val date = stringInTextField1.toIntOrNull()
        val stringInTextField2 = binding.editMonthField.text.toString()
        val month = stringInTextField2.toIntOrNull()
        val stringInTextField3 = binding.editYearField.text.toString()
        var year = stringInTextField3.toIntOrNull()

        if(date == null || date == 0 || month == null || month==0 || year==0 || year==null
            || date>31 || month>12)
        {
            display("Enter correct values")
            return
        }

        val iYear = year
        year -= 1
        year %= 400
        var oddDay=0
        val nYear = year %100
        val tYear = year / 100

        oddDay += when(tYear){
            1 -> 5
            2 -> 3
            3 -> 1
            else -> 0
        }

        val leap: Int = nYear/4
        oddDay+=(nYear+leap)

        if(iYear %100==0)
        {
            if (iYear %400==0 && month >2)
                oddDay+=1
        }
        else{
            if(iYear %4==0 && month >2)
                oddDay+=1
        }

        oddDay += when(month){
            1-> date
            2-> (3+ date)
            3-> (3+ date)
            4-> (6+ date)
            5-> (1+ date)
            6-> (4+ date)
            7-> (6+ date)
            8-> (2+ date)
            9-> (5+ date)
            10-> date
            11-> (3+ date)
            else-> (5+ date)
        }
        val p = when(oddDay%7){
            0 -> "Sunday"
            1 -> "Monday"
            2 -> "Tuesday"
            3 -> "Wednesday"
            4 -> "Thursday"
            5 -> "Friday"
            else -> "Saturday"
        }
        display(p)
    }

    private fun display(p: String){
        binding.weekResult.text = p
    }
}