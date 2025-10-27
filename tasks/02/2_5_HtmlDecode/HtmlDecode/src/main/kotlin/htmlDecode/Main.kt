package htmlDecode

fun main() {
    println("Введите текст с HTML-сущностями (для завершения нажмите Ctrl+D):")

    while (true) {
        val inputString = readlnOrNull() ?: break

        println(htmlDecode(inputString))
    }
}