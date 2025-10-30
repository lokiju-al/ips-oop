package lab_2.dictionary_2_3_2

import java.io.File

fun main(args: Array<String>) {
    if (args.isEmpty()) {
        println("Ошибка: Укажите имя файла словаря как параметр командной строки")
        return
    }

    val pathToDictionary = "${System.getProperty("user.dir")}/src/main/kotlin/lab_2/dictionary_2_3_2/dictionaries"
    val dictionaryFile = File(pathToDictionary, args[0])
    val dictionary = Dictionary(dictionaryFile)

    println("Введите слово или словосочетание на английском, чтобы получить перевод (для завершения введите строку '...')")

    while (true) {
        print(">")
        val inputString = readlnOrNull()?.trim() ?: break

        when {
            inputString == "..." -> break
            inputString.isEmpty() -> continue
            else -> {
                val translation = dictionary.translate(inputString)
                if (translation != null) {
                    println(translation)
                } else {
                    print("Неизвестное слово “$inputString”. Введите перевод или пустую строку для отказа.\n>")
                    val userTranslation = readlnOrNull()?.trim() ?: break

                    if (userTranslation.isNotEmpty()) {
                        dictionary.addTranslation(inputString, userTranslation)
                        println("Слово “$inputString” сохранено в словаре как “$userTranslation”.")
                    } else {
                        println("Слово “$inputString” проигнорировано.")
                    }
                }
            }
        }
    }

    handleExit(dictionary)
}