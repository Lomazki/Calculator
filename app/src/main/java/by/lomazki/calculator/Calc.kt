package by.lomazki.calculator

import by.lomazki.calculator.Constants.DIV
import by.lomazki.calculator.Constants.EMPTY_STRING
import by.lomazki.calculator.Constants.MINUS
import by.lomazki.calculator.Constants.MULTIPLY
import by.lomazki.calculator.Constants.NUMBER_FORMAT
import by.lomazki.calculator.Constants.PLUS
import by.lomazki.calculator.Constants.regex
import java.text.DecimalFormat

class Calc {
    private val operationList = mutableListOf<String>()
    private var numberList = mutableListOf<Double>()
    private val validator = Validator()
    private val numberFormat = DecimalFormat(NUMBER_FORMAT)

    fun calculate(input: String): SuccessOrError {
        if (input == EMPTY_STRING) {
            return SuccessOrError.Error(EMPTY_STRING)
        }
        val inputList = inputToList(input)
        validator.validate(inputList)
        if (ErrorMessage.message != EMPTY_STRING) {
            return SuccessOrError.Error(ErrorMessage.message)
        }
        filterInput(inputList)
        return SuccessOrError.Success(numberFormat.format(calculateExpression()).toString())
    }

    private fun filterInput(inputList: List<String>) {
        inputList.forEach {
            if (it.toDoubleOrNull() != null) {
                numberList.add(it.toDouble())
            } else {
                when (it) {
                    PLUS -> operationList.add(PLUS)
                    MULTIPLY -> operationList.add(MULTIPLY)
                    MINUS -> operationList.add(MINUS)
                    DIV -> operationList.add(DIV)
                    else -> println("Invalid symbol in calculator")
                }
            }
        }
    }

    private fun inputToList(value: String): List<String> {
        val matchResults = Regex(
            regex
        ).findAll(value)
        val inputToList = matchResults.map { it.value }
            .filter { it.isNotBlank() }
            .toList()

        return inputToList
    }

    private fun calculateExpression(): Double {
        var result = 0.0
        var subResult: Double? = null
        while (result != subResult) {
            subResult = calculateStep()

            operationList.removeAt(0)
            numberList.removeAt(1)
            numberList.removeAt(0)

            if (numberList.isNotEmpty()) {
                numberList.add(0, subResult)
            } else {
                result = subResult
            }
        }
        return result
    }

    private fun calculateStep(): Double {
        val result = when (operationList[0]) {
            PLUS -> numberList[0].plus(numberList[1])
            MULTIPLY -> numberList[0].times(numberList[1])
            MINUS -> numberList[0].minus(numberList[1])
            DIV -> numberList[0].div(numberList[1])
            else -> {
                -1.0
            }
        }
        return result
    }
}
