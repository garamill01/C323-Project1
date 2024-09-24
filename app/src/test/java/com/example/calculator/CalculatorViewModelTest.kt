package com.example.calculator

import androidx.lifecycle.SavedStateHandle
import org.junit.Assert.*
import org.junit.Test
import kotlin.math.exp

class CalculatorViewModelTest {

    // Test to show that the clear function puts the operand1, operand2, and operation variables
    // back to their default empty state
    @Test
    fun clear() {
        val savedStateHandle = SavedStateHandle()
        val calculatorViewModel = CalculatorViewModel(savedStateHandle)
        val expected = ""

        calculatorViewModel.clear()
        assertEquals(expected, calculatorViewModel.operand1)
        assertEquals(expected, calculatorViewModel.operand2)
        assertEquals(expected, calculatorViewModel.operation)

        calculatorViewModel.operand1 = "10"
        calculatorViewModel.operation = "+"
        calculatorViewModel.operand2 = "71"
        calculatorViewModel.clear()
        assertEquals(expected, calculatorViewModel.operand1)
        assertEquals(expected, calculatorViewModel.operand2)
        assertEquals(expected, calculatorViewModel.operation)
    }

    // Checks the addition operation for correct results, addition with negative numbers, and
    // when adding a number that contains a decimal
    @Test
    fun calculateAddition() {
        val savedStateHandle = SavedStateHandle()
        val calculatorViewModel = CalculatorViewModel(savedStateHandle)
        var expected = ""

        assertEquals(expected, calculatorViewModel.calculate())

        expected = "81"
        calculatorViewModel.operand1 = "10"
        calculatorViewModel.operation = "+"
        calculatorViewModel.operand2 = "71"
        assertEquals(expected, calculatorViewModel.calculate())

        expected = "-61"
        calculatorViewModel.operand1 = "10"
        calculatorViewModel.operation = "+"
        calculatorViewModel.operand2 = "-71"
        assertEquals(expected, calculatorViewModel.calculate())

        expected = "81"
        calculatorViewModel.operand1 = "10"
        calculatorViewModel.operation = "+"
        calculatorViewModel.operand2 = "71.0"
        assertEquals(expected, calculatorViewModel.calculate())
    }

    // Test for multiplication operation where it verifies a normal circumstance, an operation
    // between a positive and negative number, and between an integer and a double
    @Test
    fun calculateMultiplication() {
        val savedStateHandle = SavedStateHandle()
        val calculatorViewModel = CalculatorViewModel(savedStateHandle)
        var expected = "710"

        calculatorViewModel.operand1 = "10"
        calculatorViewModel.operation = "X"
        calculatorViewModel.operand2 = "71"
        assertEquals(expected, calculatorViewModel.calculate())

        expected = "-710"
        calculatorViewModel.operand1 = "10"
        calculatorViewModel.operation = "X"
        calculatorViewModel.operand2 = "-71"
        assertEquals(expected, calculatorViewModel.calculate())

        expected = "710"
        calculatorViewModel.operand1 = "10"
        calculatorViewModel.operation = "X"
        calculatorViewModel.operand2 = "71.0"
        assertEquals(expected, calculatorViewModel.calculate())
    }

    // Test for division operation where it verifies a normal circumstance, an operation
    // between a positive and negative number, and between an integer and a double
    @Test
    fun calculateDivision() {
        val savedStateHandle = SavedStateHandle()
        val calculatorViewModel = CalculatorViewModel(savedStateHandle)
        var expected = "90"

        calculatorViewModel.operand1 = "900"
        calculatorViewModel.operation = "/"
        calculatorViewModel.operand2 = "10"
        assertEquals(expected, calculatorViewModel.calculate())

        expected = "-90"
        calculatorViewModel.operand1 = "-900"
        calculatorViewModel.operation = "/"
        calculatorViewModel.operand2 = "10"
        assertEquals(expected, calculatorViewModel.calculate())

        expected = "0.5"
        calculatorViewModel.operand1 = "20"
        calculatorViewModel.operation = "/"
        calculatorViewModel.operand2 = "40"
        assertEquals(expected, calculatorViewModel.calculate())

        expected = "Error"
        calculatorViewModel.operand1 = "20"
        calculatorViewModel.operation = "/"
        calculatorViewModel.operand2 = "0"
        assertEquals(expected, calculatorViewModel.calculate())
    }

    // Test for subtraction operation where it verifies a normal circumstance, an operation
    // between a positive and negative number, and between an integer and a double
    @Test
    fun calculateSubtraction() {
        val savedStateHandle = SavedStateHandle()
        val calculatorViewModel = CalculatorViewModel(savedStateHandle)
        var expected = "72"

        calculatorViewModel.operand1 = "93"
        calculatorViewModel.operation = "-"
        calculatorViewModel.operand2 = "21"
        assertEquals(expected, calculatorViewModel.calculate())

        expected = "71.5"
        calculatorViewModel.operand1 = "93"
        calculatorViewModel.operation = "-"
        calculatorViewModel.operand2 = "21.5"
        assertEquals(expected, calculatorViewModel.calculate())

        expected = "-10"
        calculatorViewModel.operand1 = "93"
        calculatorViewModel.operation = "-"
        calculatorViewModel.operand2 = "103"
        assertEquals(expected, calculatorViewModel.calculate())
    }

    // This test verifies that the addition, subtraction, multiplication, and division buttons
    // behave in the correct way. All four are checked for their three possible logic results
    // in the operation button function: pressing an operation with only one operand, pressing
    // an operation when there is only a value in result making that value the operand in the
    // current equation, and pressing an operation when there are already two operands and an
    // operation which calculates that equation and makes the result operand1
    @Test
    fun operationButton() {
        val savedStateHandle = SavedStateHandle()
        val calculatorViewModel = CalculatorViewModel(savedStateHandle)
        var expected = ""

        assertEquals(expected, calculatorViewModel.operation)

        expected="10"
        calculatorViewModel.operand1 = "10"
        assertEquals(expected, calculatorViewModel.operationButton("+"))
        calculatorViewModel.clear()

        expected="10"
        calculatorViewModel.result = "10"
        assertEquals(expected, calculatorViewModel.operationButton("+"))
        calculatorViewModel.clear()

        expected="40"
        calculatorViewModel.operand1 = "10"
        calculatorViewModel.operation = "+"
        calculatorViewModel.operand2 = "30"
        assertEquals(expected, calculatorViewModel.operationButton("+"))
        calculatorViewModel.clear()

        expected="10"
        calculatorViewModel.operand1 = "10"
        assertEquals(expected, calculatorViewModel.operationButton("-"))
        calculatorViewModel.clear()

        expected="10"
        calculatorViewModel.result = "10"
        assertEquals(expected, calculatorViewModel.operationButton("-"))
        calculatorViewModel.clear()

        expected="20"
        calculatorViewModel.operand1 = "30"
        calculatorViewModel.operation = "-"
        calculatorViewModel.operand2 = "10"
        assertEquals(expected, calculatorViewModel.operationButton("-"))
        calculatorViewModel.clear()

        expected="10"
        calculatorViewModel.operand1 = "10"
        assertEquals(expected, calculatorViewModel.operationButton("X"))
        calculatorViewModel.clear()

        expected="10"
        calculatorViewModel.result = "10"
        assertEquals(expected, calculatorViewModel.operationButton("X"))
        calculatorViewModel.clear()

        expected="300"
        calculatorViewModel.operand1 = "10"
        calculatorViewModel.operation = "X"
        calculatorViewModel.operand2 = "30"
        assertEquals(expected, calculatorViewModel.operationButton("X"))
        calculatorViewModel.clear()

        expected="10"
        calculatorViewModel.operand1 = "10"
        assertEquals(expected, calculatorViewModel.operationButton("/"))
        calculatorViewModel.clear()

        expected="10"
        calculatorViewModel.result = "10"
        assertEquals(expected, calculatorViewModel.operationButton("/"))
        calculatorViewModel.clear()

        expected="3"
        calculatorViewModel.operand1 = "30"
        calculatorViewModel.operation = "/"
        calculatorViewModel.operand2 = "10"
        assertEquals(expected, calculatorViewModel.operationButton("/"))
        calculatorViewModel.clear()

    }

    // This test verifies that the number buttons correctly concatenate to the given operand
    @Test
    fun numberButton() {
        val savedStateHandle = SavedStateHandle()
        val calculatorViewModel = CalculatorViewModel(savedStateHandle)
        var expected = "1"

        assertEquals(expected,calculatorViewModel.numberButton("1"))

        expected = "12345"
        calculatorViewModel.numberButton("2")
        calculatorViewModel.numberButton("3")
        calculatorViewModel.numberButton("4")
        assertEquals(expected, calculatorViewModel.numberButton("5"))

    }

    // This test verifies that the percentage button divides the current operand by 100, and can
    // result in a decimal number.
    @Test
    fun percentButton() {
        val savedStateHandle = SavedStateHandle()
        val calculatorViewModel = CalculatorViewModel(savedStateHandle)
        var expected = "0"

        calculatorViewModel.operand1 = "0"
        assertEquals(expected, calculatorViewModel.percentButton())
        calculatorViewModel.clear()

        expected = "1"
        calculatorViewModel.operand1 = "100"
        assertEquals(expected, calculatorViewModel.percentButton())
        calculatorViewModel.clear()

        expected = "0.1"
        calculatorViewModel.operand1 = "10"
        assertEquals(expected, calculatorViewModel.percentButton())
        calculatorViewModel.clear()

    }

    // This test verifies that a zero cannot be negative, and verifies that it switches the given
    // operand between negative and positive based on its current state
    @Test
    fun plusMinusButton() {
        val savedStateHandle = SavedStateHandle()
        val calculatorViewModel = CalculatorViewModel(savedStateHandle)
        var expected = "0"

        calculatorViewModel.operand1 = "0"
        assertEquals(expected, calculatorViewModel.plusMinusButton())
        calculatorViewModel.clear()

        expected = "-10"
        calculatorViewModel.operand1 = "10"
        assertEquals(expected, calculatorViewModel.plusMinusButton())
        expected = "10"
        assertEquals(expected, calculatorViewModel.plusMinusButton())

        expected = "-10.5"
        calculatorViewModel.operand1 = "10.5"
        assertEquals(expected, calculatorViewModel.plusMinusButton())
        expected = "10.5"
        assertEquals(expected, calculatorViewModel.plusMinusButton())

    }

    // This test verifies that there can only be one decimal point in a give operand, as well as
    // correctly concatenating
    @Test
    fun decimalButton() {
        val savedStateHandle = SavedStateHandle()
        val calculatorViewModel = CalculatorViewModel(savedStateHandle)
        var expected = "10."

        calculatorViewModel.operand1 = "10"
        assertEquals(expected, calculatorViewModel.decimalButton())
        clear()

        expected = ""
        calculatorViewModel.operand1 = "10.23"
        assertEquals(expected, calculatorViewModel.decimalButton())
        clear()
    }
}