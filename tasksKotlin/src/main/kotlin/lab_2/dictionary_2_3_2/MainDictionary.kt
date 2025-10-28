package lab_2.dictionary_2_3_2

import java.io.File
import java.util.*

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
                    } else {
                        println("Слово “$inputString” проигнорировано.")
                    }
                }
            }
        }
    }

    if (dictionary.hasModifications()) {
        print("В словарь были внесены изменения. Введите Y или y для сохранения перед выходом.\n>")

        val saveChoice = readlnOrNull()?.trim()?.lowercase(Locale.getDefault())

        if (saveChoice == "y") {
            try {
                dictionary.saveDictionary()

                print("Изменения сохранены. До свидания.")
            } catch (e: Exception) {
                println("Ошибка при сохранении словаря: ${e.message}")
            }
        } else {
            print("Изменения не сохранены. До свидания.")
        }
    }
}