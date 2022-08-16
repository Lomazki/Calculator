package by.lomazki.calculator

import java.text.DecimalFormat

class Calc {
    private val operationList = mutableListOf<String>()
    private var numberList = mutableListOf<Double>()
    private val validator = Validator()
    private val numberFormat = DecimalFormat(NUMBER_FORMAT)

    fun calculate(input: String): String {
        if (input == EMPTY_STRING) {
            return EMPTY_STRING
        }
        val inputList = inputToList(input)
        validator.validate(inputList)
        if (ErrorMessage.messageList.isNotEmpty()) {
            return ErrorMessage.messageList[0]
        }
        filterInput(inputList)
        return numberFormat.format(calculateExpression()).toString()
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