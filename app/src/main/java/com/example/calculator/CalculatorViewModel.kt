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

    var operand1: String = ""
    var operand2: String = ""
    var operation: String = ""
    var result: String = ""
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

        // This portion of the function applies the operation to the operands and outputs the
        // resulting value
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
        if (result.contains(".")) {
            if (result.substring(result.length - 2) == ".0") {
                result = result.substring(0, result.length - 2)
            }
        }
        if (result.contains(".") && result.length > 16) {
            result = result.substring(0, 17)
        }

        return result
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
            if (operand2.length < 16) {
                operand2 += num
            }
            return operand2
        }
        else if (operand1.length < 16){
            operand1 += num
        }
        return operand1
    }

    fun percentButton() : String {
        if (operand1 != "" && operand2 != "") {
            operand2 = (operand2.toDouble() / 100).toString()
            if (operand2.contains(".")) {
                if (operand2.substring(operand2.length - 2) == ".0") {
                    operand2 = operand2.substring(0, operand2.length - 2)
                }
            }
            return operand2
        } else if (operand2 == "" && operand1 == "" && result != "") {
            operand1 = result
            result = ""
            operand1 = (operand1.toDouble() / 100).toString()
            if (operand1.contains(".")) {
                if (operand1.substring(operand1.length - 2) == ".0") {
                    operand1 = operand1.substring(0, operand1.length - 2)
                }
            }
            return operand1
        } else if (operand1 != "" && operation == "") {
            operand1 = (operand1.toDouble() / 100).toString()
            if (operand1.contains(".")) {
                if (operand1.substring(operand1.length - 2) == ".0") {
                    operand1 = operand1.substring(0, operand1.length - 2)
                }
            }
            return operand1
        } else {
            return ""
        }
    }

    fun plusMinusButton() : String {
        if (operand1 != "" && operation == "") {
            if (operand1 == "0") {
                return operand1
            }
            operand1 = if (operand1.substring(0, 1) == "-") {
                operand1.substring(1)
            } else {
                "-$operand1"
            }
            return operand1
        } else if (operand2 != ""){
            if (operand2 == "0") {
                return operand2
            }
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