package lab_2.dictionary_2_3_2

import java.util.*

fun handleExit(dictionary: Dictionary) {
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