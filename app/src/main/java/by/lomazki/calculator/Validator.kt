package by.lomazki.calculator

class Validator {

    private var errorList = mutableListOf<String>()

    fun validate(exprList: List<String>): String {
        if (errorList.isNotEmpty()) {
            errorList.clear()
        }
        isExprInvalid(exprList)
        isOperatorStart(exprList)
        isDoubleOperator(exprList)
        isDivNull(exprList)
        if (errorList.contains(ERROR)) {
            return ERROR
        }
        return EMPTY_STRING
    }

    private fun isExprInvalid(exprList: List<String>) {  // выражение не полное. нет слогаемого
        if (!exprList.last().toCharArray()[0].isDigit()) {
            errorList.add(ERROR)
        }
    }

    private fun isOperatorStart(expr: List<String>) {    // строка начинается с оператора
        if (!expr[0].toCharArray()[0].isDigit()) {
            errorList.add(ERROR)
        }
    }

    private fun isDoubleOperator(expr: List<String>) {    // две операции подряд
        var operation = ONE
        expr.forEach {
            if (!it.toCharArray()[0].isDigit() && !operation.toCharArray()[0].isDigit()) {
                errorList.add(ERROR)
            }
            operation = it
        }
    }

    fun isDivNull(expr: List<String>) {
        var div = EMPTY_STRING
        expr.forEach {
            if (it == ZERO && div == DIV) {
                errorList.add(ERROR)
            }
            div = it
        }
    }
}