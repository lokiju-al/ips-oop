package lab_2.dictionary_2_3_2_Bidirectional

import java.io.File

private fun handleExit(dictionary: Dictionary) {
    if (dictionary.hasModifications()) {
        print("В словарь были внесены изменения. Введите Y или y для сохранения перед выходом.\n>")

        val saveChoice = readlnOrNull()?.trim()

        if (saveChoice == "Y" || saveChoice == "y") {
            try {
                dictionary.saveDictionary()

                print("Изменения сохранены. ")
            } catch (e: Exception) {
                println("Ошибка при сохранении словаря: ${e.message}.")
            }
        } else {
            print("Изменения не сохранены. ")
        }
    }

    print("До свидания.")
}

private fun handleMissingTranslation(phrase: String, dictionary: Dictionary, isEnglishPhrase: Boolean) {
    print("Неизвестное слово “$phrase”. Введите перевод или пустую строку для отказа.\n>")
    val userTranslation = readlnOrNull()?.trim()

    if (!userTranslation.isNullOrEmpty()) {
        dictionary.addTranslation(phrase, userTranslation, isEnglishPhrase)

        println("Слово “$phrase” сохранено в словаре как “$userTranslation”.")
    } else {
        println("Слово “$phrase” проигнорировано.")
    }
}

private fun handleTranslation(phrase: String, dictionary: Dictionary) {
    val isEnglishPhrase = phrase.matches(Regex("[a-zA-Z\\s]+"))
    val translations = dictionary.translate(phrase, isEnglishPhrase)

    if (translations.isNotEmpty()) {
        println(translations.joinToString(", "))
    } else {
        handleMissingTranslation(phrase, dictionary, isEnglishPhrase)
    }
}

fun main(args: Array<String>) {
    if (args.isEmpty()) {
        println("Ошибка: Укажите имя файла словаря как параметр командной строки")
        return
    }

    val directoryPath =
        "${System.getProperty("user.dir")}/src/main/kotlin/lab_2/dictionary_2_3_2_Bidirectional/dictionaries"
    val dictionaryFile = File(directoryPath, args[0])
    val dictionary = Dictionary(dictionaryFile)

    println("Введите слово или словосочетание, чтобы получить перевод (для завершения введите строку '...')")

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