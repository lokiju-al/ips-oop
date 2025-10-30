package lab_2.dictionary_2_3_2_Bidirectional

fun handleTranslation(phrase: String, dictionary: Dictionary) {
    val isEnglishPhrase = phrase.matches(Regex("[a-zA-Z\\s]+"))
    val translations = dictionary.translate(phrase, isEnglishPhrase)

    if (translations.isNotEmpty()) {
        if (translations.size == 1) {
            println(translations.first())
        } else {
            println(translations.joinToString(", "))
        }
    } else {
        handleMissingTranslation(phrase, dictionary, isEnglishPhrase)
    }
}