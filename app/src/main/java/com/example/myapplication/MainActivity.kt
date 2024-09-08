package com.example.myapplication

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var resultTextView: TextView
    private lateinit var inputEditText: EditText

    private var operator = ""
    private var firstNumber = ""
    private var secondNumber = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        resultTextView = findViewById(R.id.hasil)
        inputEditText = findViewById(R.id.input)

        val buttonOne: Button = findViewById(R.id.satu)
        val buttonTwo: Button = findViewById(R.id.dua)
        val buttonThree: Button = findViewById(R.id.tiga)
        val buttonFour: Button = findViewById(R.id.empat)
        val buttonFive: Button = findViewById(R.id.lima)
        val buttonSix: Button = findViewById(R.id.enam)
        val buttonSeven: Button = findViewById(R.id.tujuh)
        val buttonEight: Button = findViewById(R.id.delapan)
        val buttonNine: Button = findViewById(R.id.sembilan)
        val buttonZero: Button = findViewById(R.id.nol)
        val buttonPlus: Button = findViewById(R.id.plus)
        val buttonMinus: Button = findViewById(R.id.minus)
        val buttonX: Button = findViewById(R.id.X)
        val buttonDivide: Button = findViewById(R.id.garis)
        val buttonPercent: Button = findViewById(R.id.percent)
        val buttonEquals: Button = findViewById(R.id.samadengan)
        val buttonClear: Button = findViewById(R.id.ac)

        val buttons = listOf(
            buttonOne, buttonTwo, buttonThree,
            buttonFour, buttonFive, buttonSix,
            buttonSeven, buttonEight, buttonNine,
            buttonZero
        )

        buttons.forEach { button ->
            button.setOnClickListener {
                val number = (it as Button).text.toString()
                appendNumber(number)
            }
        }

        buttonPlus.setOnClickListener { setOperator("+") }
        buttonMinus.setOnClickListener { setOperator("-") }
        buttonDivide.setOnClickListener { setOperator("/") }
        buttonPercent.setOnClickListener { setOperator("%") }
        buttonX.setOnClickListener { delete() }
        buttonEquals.setOnClickListener { calculateResult() }
        buttonClear.setOnClickListener { clear() }
    }

    private fun appendNumber(number: String) {
        inputEditText.append(number)
    }

    private fun setOperator(op: String) {
        val currentText = inputEditText.text.toString()

        if (currentText.isNotEmpty() || op == "%") {
            if (op == "%") {
                // Jika operator % dipilih, lakukan perhitungan dan tampilkan hasil
                firstNumber = currentText
                operator = op
                calculateResult()
            } else {
                if (operator.isNotEmpty()) {
                    // Jika sudah ada operator, hitung hasil sebelumnya sebelum mengganti operator
                    calculateResult()
                }
                // Simpan angka pertama dan operator baru
                firstNumber = currentText
                operator = op
                // Tampilkan ekspresi lengkap di inputEditText
                inputEditText.text.clear()
                inputEditText.append(firstNumber)
                inputEditText.append(" $operator ")
            }
        }
    }



    private fun calculateResult() {
        val currentText = inputEditText.text.toString()
        if (operator.isNotEmpty() && currentText.isNotEmpty()) {
            secondNumber = currentText.split(" $operator ").last() // Ambil angka kedua dari teks
            val result = when (operator) {
                "+" -> firstNumber.toDouble() + secondNumber.toDouble()
                "-" -> firstNumber.toDouble() - secondNumber.toDouble()
                "/" -> if (secondNumber.toDouble() != 0.0) firstNumber.toDouble() / secondNumber.toDouble() else "Error"
                "%" -> (firstNumber.toDouble() * 0.01) // Hitung persen dari angka pertama
                else -> "Error"
            }
            resultTextView.text = result.toString()
            // Perbarui inputEditText untuk menampilkan hasil dan operator
            inputEditText.text.clear()
            inputEditText.append("$firstNumber $operator $secondNumber")
            operator = "" // Reset operator setelah hasil dihitung
        }
    }



    private fun clear() {
        inputEditText.text.clear()
        resultTextView.text = ""
        firstNumber = ""
        secondNumber = ""
        operator = ""
    }

    private fun delete() {
        val currentText =  inputEditText.text.toString()
        if (currentText.isNotEmpty()) {
            val newText = currentText.dropLast(1)
            inputEditText.setText(newText)
            inputEditText.setSelection(newText.length)
        }
    }

}
