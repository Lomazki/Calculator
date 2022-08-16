package by.lomazki.calculator

object ErrorMessage {
    var messageList: List<String> = listOf()

    var messageProperty: List<String>
        get() = this.messageList
        set(value) {
            this.messageList = value
        }
}


