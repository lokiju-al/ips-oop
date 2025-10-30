package lab_2.dictionary_2_3_2_Bidirectional

fun handleMissingTranslation(phrase: String, dictionary: Dictionary, isEnglishPhrase: Boolean) {
    print("Неизвестное слово “$phrase”. Введите перевод или пустую строку для отказа.\n>")
    val userTranslation = readlnOrNull()?.trim()

    if (!userTranslation.isNullOrEmpty()) {
        if (isEnglishPhrase) {
            dictionary.addTranslation(phrase, userTranslation)
        } else {
            dictionary.addTranslation(userTranslation, phrase)
        }
        println("Слово “$phrase” сохранено в словаре как “$userTranslation”.")
    } else {
        println("Слово “$phrase” проигнорировано.")
    }
}