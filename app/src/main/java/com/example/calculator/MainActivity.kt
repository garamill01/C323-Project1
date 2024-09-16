package com.example.calculator

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.calculator.databinding.ActivityMainBinding

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    // Created global variables to store the two operands and operation (+, -, X, /), along with the
    // Result variable when equals is pressed
    private var operand1: String = ""
    private var operand2: String = ""
    private var operation: String = ""
    private var result: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Calculator text view is set to 0 as default
        binding.calculatorTextView.text = "0"

        // Clear button calls the clear() function, which resets all the global variables to their
        // default state
        binding.clearButton.setOnClickListener {
            Log.d(TAG, "Clear Button Pressed")
            clear()
        }

        // Equals button makes sure there are two variables in the operands and an operation,
        // THen calls the calculate() function to process the equation.
        // The equals button also clears the operand1, operand2, and operation variables
        // but not the result variable so that it can be displayed or used as an operand.
        binding.equalsButton.setOnClickListener {
            Log.d(TAG, "Equals Button Pressed")
            if (operand1 != "" && operand2 != "" && operation != "") {
                result = calculate(operand1, operand2, operation)
                operand1 = ""
                operand2 = ""
                operation = ""
                binding.calculatorTextView.text = result
            }
        }

        // The add button works with similar logic to the subtract, divide, and multiply buttons.
        // The logic checks if an operation and two operands are already stored in the variables.
        // If so, then the program calculates those variables and places the result in operand1
        // and the pressed operation as the new operation variable, then awaits a second operand.
        binding.addButton.setOnClickListener {
            if (operand1 != "" && operand2 != "") {
                operand1 = calculate(operand1, operand2, operation)
                operand2 = ""
                operation = "+"
                binding.calculatorTextView.text = operand1
            } else if (operand2 == "" && operand1 == "" && result != "") {
                operand1 = result
                operation = "+"
                binding.calculatorTextView.text = operand1
            } else if (operand1 != "" && operation == "") {
                operation = "+"
            }
            Log.d(TAG, "Add Button Pressed: " +
                    "Op1 = $operand1, Op2 = $operand2, Operation = $operation")
        }

        binding.subtractButton.setOnClickListener {
            if (operand1 != "" && operand2 != "") {
                operand1 = calculate(operand1, operand2, operation)
                operand2 = ""
                operation = "-"
                binding.calculatorTextView.text = operand1
            } else if (operand2 == "" && operand1 == "" && result != "") {
                operand1 = result
                operation = "-"
                binding.calculatorTextView.text = operand1
            } else if (operand1 != "" && operation == "") {
                operation = "-"
            }
            Log.d(TAG, "Subtract Button Pressed: " +
                    "Op1 = $operand1, Op2 = $operand2, Operation = $operation")
        }

        binding.multiplyButton.setOnClickListener {
            if (operand1 != "" && operand2 != "") {
                operand1 = calculate(operand1, operand2, operation)
                operand2 = ""
                operation = "X"
                binding.calculatorTextView.text = operand1
            } else if (operand2 == "" && operand1 == "" && result != "") {
                operand1 = result
                operation = "X"
                binding.calculatorTextView.text = operand1
            } else if (operand1 != "" && operation == "") {
                operation = "X"
            }
            Log.d(TAG, "Multiply Button Pressed: " +
                    "Op1 = $operand1, Op2 = $operand2, Operation = $operation")
        }

        binding.divideButton.setOnClickListener {
            if (operand1 != "" && operand2 != "") {
                operand1 = calculate(operand1, operand2, operation)
                operand2 = ""
                operation = "/"
                binding.calculatorTextView.text = operand1
            } else if (operand2 == "" && operand1 == "" && result != "") {
                operand1 = result
                operation = "/"
                binding.calculatorTextView.text = operand1
            } else if (operand1 != "" && operation == "") {
                operation = "/"
            }
            Log.d(TAG, "Divide Button Pressed: " +
                    "Op1 = $operand1, Op2 = $operand2, Operation = $operation")
        }

        // The decimal button first checks which operand is being appended to, then makes sure that
        // the operand does not already contain a decimal
        binding.decimalButton.setOnClickListener {
            if (operand1 != "" && operation != "" && !operand2.contains(".")) {
                operand2 += "."
                binding.calculatorTextView.text = operand2
            }
            else if (!operand1.contains(".")){
                operand1 += "."
                binding.calculatorTextView.text = operand1
            }
            Log.d(TAG, "Decimal Button Pressed")
        }

        // The plus/minus button uses logic to check which operand is being appended, then checks
        // whether to add the minus sign or remove it.
        binding.plusMinusButton.setOnClickListener {
            if (operand1 != "" && operation == "") {
                operand1 = if (operand1.substring(0, 1) == "-") {
                    operand1.substring(1)
                } else {
                    "-$operand1"
                }
                binding.calculatorTextView.text = operand1
            } else if (operand2 != ""){
                operand2 = if (operand2.substring(0, 1) == "-") {
                    operand2.substring(1)
                } else {
                    "-$operand2"
                }
                binding.calculatorTextView.text = operand2
            }
            Log.d(TAG, "Plus/Minus Button Pressed")
        }

        binding.percentButton.setOnClickListener {
            if (operand1 != "" && operand2 != "") {
                operand2 = (operand2.toDouble() / 100).toString()
                binding.calculatorTextView.text = operand2
            } else if (operand2 == "" && operand1 == "" && result != "") {
                operand1 = result
                result = ""
                operand1 = (operand1.toDouble() / 100).toString()
                binding.calculatorTextView.text = operand1
            } else if (operand1 != "" && operation == "") {
                operand1 = (operand1.toDouble() / 100).toString()
                binding.calculatorTextView.text = operand1
            }
            Log.d(TAG, "Percent Button Pressed: " +
                    "Op1 = $operand1, Op2 = $operand2, Operation = $operation")
        }

        // Each number button uses logic to check which operand is currently being appended based on
        // if the operation variable contains a value or is an empty string
        binding.oneButton.setOnClickListener {
            if (operand1 != "" && operation != "") {
                operand2 += "1"
                binding.calculatorTextView.text = operand2
            }
            else {
                operand1 += "1"
                binding.calculatorTextView.text = operand1
            }
            Log.d(TAG, "One Button Pressed")
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
            Log.d(TAG, "Two Button Pressed")
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
            Log.d(TAG, "Three Button Pressed")
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
            Log.d(TAG, "Four Button Pressed")
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
            Log.d(TAG, "Five Button Pressed")
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
            Log.d(TAG, "Six Button Pressed")
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
            Log.d(TAG, "Seven Button Pressed")
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
            Log.d(TAG, "Eight Button Pressed")
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
            Log.d(TAG, "Nine Button Pressed")
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
            Log.d(TAG, "Zero Button Pressed")
        }
    }

    // The clear function returns the four global variables to their original state, along with the
    // text view back to only show its default state of "0"
    private fun clear() {
        operand1 = ""
        operand2 = ""
        operation = ""
        result = ""

        binding.calculatorTextView.text = "0"
    }

    // Calculate is called when either the equals button is pressed, or an operation is pressed when
    // there are already two operands and an operation are stored in the corresponding variables.
    private fun calculate(operand1: String, operand2: String, operation: String): String {
        var resultDouble = 0.0
        var result = 0

        // The function makes sure that the program does not try to divide by zero and will display
        // "Error" if this is attempted.
        if (operation == "/" && operand2 == "0") {
            clear()
            return "Error"
        }

        // This portion of the function checks whether either operand contains a decimal, deciding
        // whether the var types should be Double or Int
        if (operand1.contains(".") || operand2.contains(".")) {
            when (operation) {
                "+" -> resultDouble = operand1.toDouble() + operand2.toDouble()
                "-" -> resultDouble = operand1.toDouble() - operand2.toDouble()
                "X" -> resultDouble = operand1.toDouble() * operand2.toDouble()
                "/" -> resultDouble = operand1.toDouble() / operand2.toDouble()
            }
            Log.d(TAG, "Calculate function: Op1 = $operand1, Op2 = $operand2, Operand = $operation, result = $resultDouble")
            return resultDouble.toString()
        } else {
            when (operation) {
                "+" -> result = operand1.toInt() + operand2.toInt()
                "-" -> result = operand1.toInt() - operand2.toInt()
                "X" -> result = operand1.toInt() * operand2.toInt()
                "/" -> result = operand1.toInt() / operand2.toInt()
            }
            Log.d(TAG, "Calculate function: Op1 = $operand1, Op2 = $operand2, Operand = $operation, result = $result")
            return result.toString()
        }
    }
}