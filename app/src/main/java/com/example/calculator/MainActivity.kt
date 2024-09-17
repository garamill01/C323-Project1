package com.example.calculator

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.activity.viewModels
import com.example.calculator.databinding.ActivityMainBinding

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val calculatorViewModel: CalculatorViewModel by viewModels()

    // Created global variables to store the two operands and operation (+, -, X, /), along with the
    // Result variable when equals is pressed

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Log.d(TAG, "Got a CalculatorViewModel: $calculatorViewModel")

        // Calculator text view is set to 0 as default
        binding.calculatorTextView.text = calculatorViewModel.currentText

        // Clear button calls the clear() function, which resets all the global variables to their
        // default state
        binding.clearButton.setOnClickListener {
            Log.d(TAG, "Clear Button Pressed")
            calculatorViewModel.clear()
            updateTextBox("0")
        }

        // Equals button makes sure there are two variables in the operands and an operation,
        // THen calls the calculate() function to process the equation.
        // The equals button also clears the operand1, operand2, and operation variables
        // but not the result variable so that it can be displayed or used as an operand.
        binding.equalsButton.setOnClickListener {
            Log.d(TAG, "Equals Button Pressed")
            val calculatorText = calculatorViewModel.calculate()
            if (calculatorText != "") {
                binding.calculatorTextView.text = calculatorText
            }
        }

        // The add button works with similar logic to the subtract, divide, and multiply buttons.
        // The logic checks if an operation and two operands are already stored in the variables.
        // If so, then the program calculates those variables and places the result in operand1
        // and the pressed operation as the new operation variable, then awaits a second operand.
        binding.addButton.setOnClickListener {
            val calculatorText = calculatorViewModel.operationButton("+")
            if (calculatorText != "") {
                updateTextBox(calculatorText)
            }
        }

        binding.subtractButton.setOnClickListener {
            val calculatorText = calculatorViewModel.operationButton("-")
            if (calculatorText != "") {
                updateTextBox(calculatorText)
            }
        }

        binding.multiplyButton.setOnClickListener {
            val calculatorText = calculatorViewModel.operationButton("X")
            if (calculatorText != "") {
                updateTextBox(calculatorText)
            }
        }

        binding.divideButton.setOnClickListener {
            val calculatorText = calculatorViewModel.operationButton("/")
            if (calculatorText != "") {
                updateTextBox(calculatorText)
            }
        }

        // The decimal button first checks which operand is being appended to, then makes sure that
        // the operand does not already contain a decimal
        binding.decimalButton.setOnClickListener {
            val result = calculatorViewModel.decimalButton()
            if (result != "") {
                updateTextBox(result)
            }
            Log.d(TAG, "Decimal Button Pressed")
        }

        // The plus/minus button uses logic to check which operand is being appended, then checks
        // whether to add the minus sign or remove it.
        binding.plusMinusButton.setOnClickListener {
            val result = calculatorViewModel.plusMinusButton()
            if (result != "") {
                updateTextBox(result)
            }
            Log.d(TAG, "Plus/Minus Button Pressed")
        }

        binding.percentButton.setOnClickListener {
            val result = calculatorViewModel.percentButton()
            if (result != "") {
                updateTextBox(result)
            }
            Log.d(TAG, "Percent Button Pressed")
        }

        // All of the trigonometric buttons (sin, cos, tan) and the logarithmic buttons (log10, ln)
        // use the same function logic as the percent button, where the function checks to see the
        // values of the operand and operation variables to determine what number to apply the
        // button's function to. Then the affected value is passed along back to the binding
        // statement to where the textbox is updated, unless the button had no value to apply to.
        binding.sinButton?.setOnClickListener {
            val result = calculatorViewModel.sinButton()
            if (result != "") {
                updateTextBox(result)
            }
            Log.d(TAG, "Sin Button Pressed")
        }

        binding.cosButton?.setOnClickListener {
            val result = calculatorViewModel.cosButton()
            if (result != "") {
                updateTextBox(result)
            }
            Log.d(TAG, "Cos Button Pressed")
        }

        binding.tanButton?.setOnClickListener {
            val result = calculatorViewModel.tanButton()
            if (result != "") {
                updateTextBox(result)
            }
            Log.d(TAG, "Tan Button Pressed")
        }

        binding.log10Button?.setOnClickListener {
            val result = calculatorViewModel.log10Button()
            if (result != "") {
                updateTextBox(result)
            }
            Log.d(TAG, "Log10 Button Pressed")
        }

        binding.lnButton?.setOnClickListener {
            val result = calculatorViewModel.lnButton()
            if (result != "") {
                updateTextBox(result)
            }
            Log.d(TAG, "ln Button Pressed")
        }

        // Each number button uses logic to check which operand is currently being appended based on
        // if the operation variable contains a value or is an empty string
        binding.oneButton.setOnClickListener {
            updateTextBox(calculatorViewModel.numberButton("1"))
            Log.d(TAG, "One Button Pressed")
        }

        binding.twoButton.setOnClickListener {
            updateTextBox(calculatorViewModel.numberButton("2"))
            Log.d(TAG, "Two Button Pressed")
        }

        binding.threeButton.setOnClickListener {
            updateTextBox(calculatorViewModel.numberButton("3"))
            Log.d(TAG, "Three Button Pressed")
        }

        binding.fourButton.setOnClickListener {
            updateTextBox(calculatorViewModel.numberButton("4"))
            Log.d(TAG, "Four Button Pressed")
        }

        binding.fiveButton.setOnClickListener {
            updateTextBox(calculatorViewModel.numberButton("5"))
            Log.d(TAG, "Five Button Pressed")
        }

        binding.sixButton.setOnClickListener {
            updateTextBox(calculatorViewModel.numberButton("6"))
            Log.d(TAG, "Six Button Pressed")
        }

        binding.sevenButton.setOnClickListener {
            updateTextBox(calculatorViewModel.numberButton("7"))
            Log.d(TAG, "Seven Button Pressed")
        }

        binding.eightButton.setOnClickListener {
            updateTextBox(calculatorViewModel.numberButton("8"))
            Log.d(TAG, "Eight Button Pressed")
        }

        binding.nineButton.setOnClickListener {
            updateTextBox(calculatorViewModel.numberButton("9"))
            Log.d(TAG, "Nine Button Pressed")
        }

        binding.zeroButton.setOnClickListener {
            updateTextBox(calculatorViewModel.numberButton("0"))
            Log.d(TAG, "Zero Button Pressed")
        }
    }

    private fun updateTextBox(text: String) {
        calculatorViewModel.currentText = text
        binding.calculatorTextView.text = text
    }
}



