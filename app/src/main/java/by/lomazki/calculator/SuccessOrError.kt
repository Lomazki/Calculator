package by.lomazki.calculator

sealed class SuccessOrError {

    class Success(val result: String) : SuccessOrError()
    class Error(val error: String) : SuccessOrError()

}