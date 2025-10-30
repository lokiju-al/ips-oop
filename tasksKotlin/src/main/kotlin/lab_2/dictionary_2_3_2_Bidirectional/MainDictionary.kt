package lab_2.dictionary_2_3_2_Bidirectional

import java.io.File

fun main(args: Array<String>) {
    if (args.isEmpty()) {
        println("Ошибка: Укажите имя файла словаря как параметр командной строки")
        return
    }

    val pathToDictionary =
        "${System.getProperty("user.dir")}/src/main/kotlin/lab_2/dictionary_2_3_2_Bidirectional/dictionaries"
    val dictionaryFile = File(pathToDictionary, args[0])
    val dictionary = Dictionary(dictionaryFile)

    println("Введите слово или словосочетание на английском или русском, чтобы получить перевод (для завершения введите строку '...')")

    while (true) {
        print(">")
        val inputString = readlnOrNull()?.trim() ?: break

        when {
            inputString == "..." -> break
            inputString.isEmpty() -> continue
            else -> handleTranslation(inputString, dictionary)
        }
    }

    handleExit(dictionary)
}