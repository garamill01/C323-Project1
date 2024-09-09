package com.example.calculator

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.calculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private var operand1: String? = ""
    private var operand2: String? = ""
    private var operation: String? = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.calculatorTextView.setText(0)

        binding.clearButton.setOnClickListener {
            clear()
        }

        binding.equalsButton.setOnClickListener {
            if (operation == "/" && operand2 == "0") {
                clear()
                binding.calculatorTextView.text = getResources().getString(R.string.error_message)
            }
            if (operand1 != null && operand2 != null && operation != null) {
                val result: Int = calculate(operand1!!, operand2!!, operation!!)
                binding.calculatorTextView.setText(result)
            }
        }

        binding.oneButton.setOnClickListener {
            if (operand1 != "" && operation != "") {
                operand2 += "1"
                binding.calculatorTextView.text = operand2
            }
            else {
                operand1 += "1"
                binding.calculatorTextView.text = operand1
            }
        }

        binding.twoButton.setOnClickListener {
            if (operand1 != "" && operation != "") {
                operand2 += "2"
                binding.calculatorTextView.text = operand2
            }
            else {
                operand1 += "2"
                binding.calculatorTextView.text = operand1
            }
        }

        binding.threeButton.setOnClickListener {
            if (operand1 != "" && operation != "") {
                operand2 += "3"
                binding.calculatorTextView.text = operand2
            }
            else {
                operand1 += "3"
                binding.calculatorTextView.text = operand1
            }
        }

        binding.fourButton.setOnClickListener {
            if (operand1 != "" && operation != "") {
                operand2 += "4"
                binding.calculatorTextView.text = operand2
            }
            else {
                operand1 += "4"
                binding.calculatorTextView.text = operand1
            }
        }

        binding.fiveButton.setOnClickListener {
            if (operand1 != "" && operation != "") {
                operand2 += "5"
                binding.calculatorTextView.text = operand2
            }
            else {
                operand1 += "5"
                binding.calculatorTextView.text = operand1
            }
        }

        binding.sixButton.setOnClickListener {
            if (operand1 != "" && operation != "") {
                operand2 += "6"
                binding.calculatorTextView.text = operand2
            }
            else {
                operand1 += "6"
                binding.calculatorTextView.text = operand1
            }
        }

        binding.sevenButton.setOnClickListener {
            if (operand1 != "" && operation != "") {
                operand2 += "7"
                binding.calculatorTextView.text = operand2
            }
            else {
                operand1 += "7"
                binding.calculatorTextView.text = operand1
            }
        }

        binding.eightButton.setOnClickListener {
            if (operand1 != "" && operation != "") {
                operand2 += "8"
                binding.calculatorTextView.text = operand2
            }
            else {
                operand1 += "8"
                binding.calculatorTextView.text = operand1
            }
        }

        binding.nineButton.setOnClickListener {
            if (operand1 != "" && operation != "") {
                operand2 += "9"
                binding.calculatorTextView.text = operand2
            }
            else {
                operand1 += "9"
                binding.calculatorTextView.text = operand1
            }
        }

        binding.zeroButton.setOnClickListener {
            if (operand1 != "" && operation != "") {
                operand2 += "0"
                binding.calculatorTextView.text = operand2
            }
            else {
                operand1 += "0"
                binding.calculatorTextView.text = operand1
            }
        }
    }

    private fun clear() {
        operand1 = ""
        operand2 = ""
        operation = ""

        binding.calculatorTextView.setText(0)
    }

    private fun calculate(operand1: String, operand2: String, operation: String): Int {
        var result = 0

        when (operation) {
            "+" -> result = operand1.toInt() + operand2.toInt()
            "-" -> result = operand1.toInt() - operand2.toInt()
            "X" -> result = operand1.toInt() * operand2.toInt()
            "/" -> result = operand1.toInt() / operand2.toInt()
        }
        return result
    }
}