package com.example.tugas1kalkulator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private lateinit var resultTextView: TextView
    private var firstNumber: Double = 0.0
    private var operation: String = ""
    private var isNewOperation: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        resultTextView = findViewById(R.id.resultTextView)

        // Inisialisasi tombol angka
        val numberButtons = arrayOf(
            R.id.button0, R.id.button1, R.id.button2, R.id.button3, R.id.button4,
            R.id.button5, R.id.button6, R.id.button7, R.id.button8, R.id.button9
        )

        for (buttonId in numberButtons) {
            findViewById<Button>(buttonId).setOnClickListener { onNumberClick((it as Button).text.toString()) }
        }

        // Inisialisasi tombol operasi
        findViewById<Button>(R.id.buttonPlus).setOnClickListener { onOperationClick("+") }
        findViewById<Button>(R.id.buttonMinus).setOnClickListener { onOperationClick("-") }
        findViewById<Button>(R.id.buttonMultiply).setOnClickListener { onOperationClick("*") }
        findViewById<Button>(R.id.buttonDivide).setOnClickListener { onOperationClick("/") }

        // Tombol sama dengan (=)
        findViewById<Button>(R.id.buttonEquals).setOnClickListener { onEqualsClick() }

        // Tombol clear (C)
        findViewById<Button>(R.id.buttonClear).setOnClickListener { onClearClick() }
    }

    private fun onNumberClick(number: String) {
        if (isNewOperation) {
            resultTextView.text = number
            isNewOperation = false
        } else {
            resultTextView.append(number)
        }
    }

    private fun onOperationClick(op: String) {
        firstNumber = resultTextView.text.toString().toDouble()
        operation = op
        isNewOperation = true
    }

    private fun onEqualsClick() {
        if (operation.isNotEmpty()) {
            val secondNumber = resultTextView.text.toString().toDouble()
            val result = when (operation) {
                "+" -> firstNumber + secondNumber
                "-" -> firstNumber - secondNumber
                "*" -> firstNumber * secondNumber
                "/" -> if (secondNumber != 0.0) firstNumber / secondNumber else Double.NaN
                else -> Double.NaN
            }
            resultTextView.text = if (result.isNaN()) "Error" else result.toString()
            operation = ""
            isNewOperation = true
        }
    }

    private fun onClearClick() {
        resultTextView.text = "0"
        firstNumber = 0.0
        operation = ""
        isNewOperation = true
    }
}