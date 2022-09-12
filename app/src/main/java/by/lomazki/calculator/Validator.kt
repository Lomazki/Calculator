package by.lomazki.calculator

class Validator {

    fun validate(exprList: List<String>) {
        if (ErrorMessage.messageList.isNotEmpty()) {
            ErrorMessage.messageList = listOf()
        }

        isExprInvalid(exprList)
        isOperatorStart(exprList)
        isDoubleOperator(exprList)
        isDivNull(exprList)

    }

    private fun isExprInvalid(exprList: List<String>) {     // выражение не полное. нет слогаемого
        if (!exprList.last().toCharArray()[0].isDigit()) {
            addError(END_IS_NOT_DIGIT)
        }
    }

    private fun isOperatorStart(expr: List<String>) {       // строка начинается с оператора
        if (!expr[0].toCharArray()[0].isDigit()) {
            addError(START_IS_NOT_DIGIT)
        }
    }

    private fun isDoubleOperator(expr: List<String>) {      // две операции подряд
        var operation = ONE
        expr.forEach {
            if (!it.toCharArray()[0].isDigit() && !operation.toCharArray()[0].isDigit()) {
                addError(MISTAKE_OPERATION)
            }
            operation = it
        }
    }

    private fun isDivNull(expr: List<String>) {             // деление на ноль
        var div = EMPTY_STRING
        expr.forEach {
            if (it == ZERO && div == DIV) {
                addError(DIVISION_BY_ZERO)
            }
            div = it
        }
    }

    private fun addError(error: String) {
        val messageProperty: MutableList<String> = ErrorMessage.messageProperty.toMutableList()
        messageProperty.add(error)
        ErrorMessage.messageList = messageProperty
    }
}