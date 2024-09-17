package com.example.calculator

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import kotlin.math.sin
import kotlin.math.cos
import kotlin.math.ln
import kotlin.math.log10
import kotlin.math.tan

private const val TAG = "CalculatorViewModel"

class CalculatorViewModel(private val savedStateHandle: SavedStateHandle) : ViewModel() {

    private var operand1: String = ""
    private var operand2: String = ""
    private var operation: String = ""
    private var result: String = ""
    var currentText: String = "0"

    // The clear function returns the four global variables to their original state, along with the
    // text view back to only show its default state of "0"
    fun clear() {
        operand1 = ""
        operand2 = ""
        operation = ""
        result = ""
    }

    // Calculate is called when either the equals button is pressed, or an operation is pressed when
    // there are already two operands and an operation are stored in the corresponding variables.
    fun calculate(): String {
        var resultDouble = 0.0
        var resultInt = 0

        // The function makes sure that the program does not try to divide by zero and will display
        // "Error" if this is attempted.
        if (operation == "/" && operand2 == "0") {
            clear()
            return "Error"
        }
        if (operand1 == "" || operand2 == "" || operation == "") {
            return ""
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
            operand1 = ""
            operand2 = ""
            operation = ""
            result = resultDouble.toString()
            return result
        } else {
            when (operation) {
                "+" -> resultInt = operand1.toInt() + operand2.toInt()
                "-" -> resultInt = operand1.toInt() - operand2.toInt()
                "X" -> resultInt = operand1.toInt() * operand2.toInt()
                "/" -> resultInt = operand1.toInt() / operand2.toInt()
            }
            Log.d(TAG, "Calculate function: Op1 = $operand1, Op2 = $operand2, Operand = $operation, result = $result")
            operand1 = ""
            operand2 = ""
            operation = ""
            result = resultInt.toString()
            return result
        }
    }

    fun operationButton(op: String) : String {
        if (operand1 != "" && operand2 != "") {
            operand1 = calculate()
            operand2 = ""
            operation = op
            return operand1
        } else if (operand2 == "" && operand1 == "" && result != "") {
            operand1 = result
            operation = op
            return operand1
        } else if (operand1 != "" && operation == "") {
            operation = op
            return operand1
        }
        Log.d(TAG, "$op Button Pressed: " +
                "Op1 = $operand1, Op2 = $operand2, Operation = $operation")
        return ""
    }

    fun numberButton(num: String) : String {
        if (operand1 != "" && operation != "") {
            operand2 += num
            return operand2
        }
        else {
            operand1 += num
            return operand1
        }
    }

    fun percentButton() : String {
        if (operand1 != "" && operand2 != "") {
            operand2 = (operand2.toDouble() / 100).toString()
            return operand2
        } else if (operand2 == "" && operand1 == "" && result != "") {
            operand1 = result
            result = ""
            operand1 = (operand1.toDouble() / 100).toString()
            return operand1
        } else if (operand1 != "" && operation == "") {
            operand1 = (operand1.toDouble() / 100).toString()
            return operand1
        } else {
            return ""
        }
    }

    fun plusMinusButton() : String {
        if (operand1 != "" && operation == "") {
            operand1 = if (operand1.substring(0, 1) == "-") {
                operand1.substring(1)
            } else {
                "-$operand1"
            }
            return operand1
        } else if (operand2 != ""){
            operand2 = if (operand2.substring(0, 1) == "-") {
                operand2.substring(1)
            } else {
                "-$operand2"
            }
            return operand2
        } else {
            return ""
        }
    }

    fun decimalButton() : String {
        if (operand1 != "" && operation != "" && !operand2.contains(".")) {
            operand2 += "."
            return operand2
        }
        else if (!operand1.contains(".")){
            operand1 += "."
            return operand1
        }
        return ""
    }

    fun sinButton() : String {
        if (operand1 != "" && operand2 != "") {
            operand2 = (sin(operand2.toDouble())).toString()
            return operand2
        } else if (operand2 == "" && operand1 == "" && result != "") {
            operand1 = result
            result = ""
            operand1 = (sin(operand1.toDouble())).toString()
            return operand1
        } else if (operand1 != "" && operation == "") {
            operand1 = (sin(operand1.toDouble())).toString()
            return operand1
        } else {
            return ""
        }
    }

    fun cosButton() : String {
        if (operand1 != "" && operand2 != "") {
            operand2 = (cos(operand2.toDouble())).toString()
            return operand2
        } else if (operand2 == "" && operand1 == "" && result != "") {
            operand1 = result
            result = ""
            operand1 = (cos(operand1.toDouble())).toString()
            return operand1
        } else if (operand1 != "" && operation == "") {
            operand1 = (cos(operand1.toDouble())).toString()
            return operand1
        } else {
            return ""
        }
    }

    fun tanButton() : String {
        if (operand1 != "" && operand2 != "") {
            operand2 = (tan(operand2.toDouble())).toString()
            return operand2
        } else if (operand2 == "" && operand1 == "" && result != "") {
            operand1 = result
            result = ""
            operand1 = (tan(operand1.toDouble())).toString()
            return operand1
        } else if (operand1 != "" && operation == "") {
            operand1 = (tan(operand1.toDouble())).toString()
            return operand1
        } else {
            return ""
        }
    }

    fun log10Button() : String {
        if (operand1 != "" && operand2 != "") {
            operand2 = (log10(operand2.toDouble())).toString()
            return operand2
        } else if (operand2 == "" && operand1 == "" && result != "") {
            operand1 = result
            result = ""
            operand1 = (log10(operand1.toDouble())).toString()
            return operand1
        } else if (operand1 != "" && operation == "") {
            operand1 = (log10(operand1.toDouble())).toString()
            return operand1
        } else {
            return ""
        }
    }

    fun lnButton() : String {
        if (operand1 != "" && operand2 != "") {
            operand2 = (ln(operand2.toDouble())).toString()
            return operand2
        } else if (operand2 == "" && operand1 == "" && result != "") {
            operand1 = result
            result = ""
            operand1 = (ln(operand1.toDouble())).toString()
            return operand1
        } else if (operand1 != "" && operation == "") {
            operand1 = (ln(operand1.toDouble())).toString()
            return operand1
        } else {
            return ""
        }
    }

    override fun onCleared() {
        super.onCleared()
        Log.d(TAG, "ViewModel instance about to be destroyed")
    }
}