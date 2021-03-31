package se.magictechnology.pia9date

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.CalendarView
import android.widget.DatePicker
import android.widget.Toast
import java.util.*
import java.util.concurrent.TimeUnit
import javax.xml.datatype.DatatypeConstants.MONTHS

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        var calView = findViewById<CalendarView>(R.id.calendarView)

        calView.setOnDateChangeListener { view, year, month, dayOfMonth ->
            Log.d("pia9debug", dayOfMonth.toString())
        }


        val datePicker = findViewById<DatePicker>(R.id.datePicker)
        val today = Calendar.getInstance()

        datePicker.init(today.get(Calendar.YEAR), today.get(Calendar.MONTH),
                today.get(Calendar.DAY_OF_MONTH)
        ) { view, year, month, day ->
            val month = month + 1
            val msg = "You Selected: $day/$month/$year"
            Toast.makeText(this@MainActivity, msg, Toast.LENGTH_SHORT).show()

            //Log.d("pia9debug", )



        }



        findViewById<Button>(R.id.selectDateButton).setOnClickListener {
            val c = Calendar.getInstance()
            val year = c.get(Calendar.YEAR)
            val month = c.get(Calendar.MONTH)
            val day = c.get(Calendar.DAY_OF_MONTH)


            val dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                Log.d("pia9debug", dayOfMonth.toString())

                var selectedDate = Calendar.getInstance()
                selectedDate.set(year, monthOfYear, dayOfMonth)

                var christmas = Calendar.getInstance()
                christmas.set(year, 11, 24)

                var millisToChristmas = christmas.time.time - selectedDate.time.time

                Log.d("pia9debug", millisToChristmas.toString())

                val oneDay = 24*60*60*1000

                val daysToChristmas = millisToChristmas/oneDay

                Log.d("pia9debug", daysToChristmas.toString())

                var daysLeft = TimeUnit.MILLISECONDS.toDays(millisToChristmas)

                Log.d("pia9debug", daysLeft.toString())


            }, year, month, day)


            dpd.show()


        }

        findViewById<Button>(R.id.selectTimeButton).setOnClickListener {
            val tpd = TimePickerDialog(this, TimePickerDialog.OnTimeSetListener { view, thehour, theminute ->

            }, 1, 1, true)

            tpd.show()
        }

    }
}