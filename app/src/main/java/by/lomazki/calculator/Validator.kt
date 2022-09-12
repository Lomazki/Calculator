package by.lomazki.calculator

import by.lomazki.calculator.Constants.DIV
import by.lomazki.calculator.Constants.DIVISION_BY_ZERO
import by.lomazki.calculator.Constants.EMPTY_STRING
import by.lomazki.calculator.Constants.END_IS_NOT_DIGIT
import by.lomazki.calculator.Constants.MISTAKE_OPERATION
import by.lomazki.calculator.Constants.ONE
import by.lomazki.calculator.Constants.START_IS_NOT_DIGIT
import by.lomazki.calculator.Constants.ZERO

class Validator {

    fun validate(exprList: List<String>) {
        ErrorMessage.message = EMPTY_STRING

        isExprInvalid(exprList)
        isOperatorStart(exprList)
        isDoubleOperator(exprList)
        isDivNull(exprList)
    }

    private fun isExprInvalid(exprList: List<String>) {     // выражение не полное. нет слогаемого
        if (!exprList.last().toCharArray()[0].isDigit()) {
            ErrorMessage.message = END_IS_NOT_DIGIT
        }
    }

    private fun isOperatorStart(expr: List<String>) {       // строка начинается с оператора
        if (!expr[0].toCharArray()[0].isDigit()) {
            ErrorMessage.message = START_IS_NOT_DIGIT
        }
    }

    private fun isDoubleOperator(expr: List<String>) {      // две операции подряд
        var operation = ONE
        expr.forEach {
            if (!it.toCharArray()[0].isDigit() && !operation.toCharArray()[0].isDigit()) {
                ErrorMessage.message = MISTAKE_OPERATION
            }
            operation = it
        }
    }

    private fun isDivNull(expr: List<String>) {             // деление на ноль
        var div = EMPTY_STRING
        expr.forEach {
            if (it == ZERO && div == DIV) {
                ErrorMessage.message = DIVISION_BY_ZERO
            }
            div = it
        }
    }
}