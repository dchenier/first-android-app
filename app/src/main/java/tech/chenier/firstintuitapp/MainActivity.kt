package tech.chenier.firstintuitapp

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.widget.doAfterTextChanged
import androidx.core.widget.doOnTextChanged
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var _addedPeople = 0;
    private var _removedPeople = 0;

    private fun setPeopleCount() {
        val totalPeople = this._addedPeople - this._removedPeople
        txtTotalPeople.text = "There are ${totalPeople} people inside"
        val stadiumCapacity = try {
            txtCapacity.text.toString().toInt()
        } catch (_: Exception) {
            100
        }
        val percentFull = totalPeople * 100 / stadiumCapacity
        if (percentFull < 80) {
            txtTotalPeople.setBackgroundColor(Color.TRANSPARENT)
        } else if (percentFull <= 100) {
            txtTotalPeople.setBackgroundColor(Color.YELLOW)
        } else {
            txtTotalPeople.setBackgroundColor(Color.RED)
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnAddPerson.setOnClickListener {
            _addedPeople++
            setPeopleCount()
        }

        btnRemovePerson.setOnClickListener {
            _removedPeople++
            setPeopleCount()
        }

        btnClear.setOnClickListener {
            _addedPeople = 0
            _removedPeople = 0
            setPeopleCount()
        }

        txtCapacity.doAfterTextChanged {
            setPeopleCount()
        }

    }
}