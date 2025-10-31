package lab_2.dictionary_2_3_2_Bidirectional

import java.io.File
import java.util.*

class Dictionary(private val dictionaryFile: File) {
    private val englishToRussian = mutableMapOf<String, MutableSet<String>>()
    private val russianToEnglish = mutableMapOf<String, MutableSet<String>>()
    private var isModified = false

    init {
        loadDictionary()
    }

    private fun loadDictionary() {
        if (dictionaryFile.exists()) {
            dictionaryFile.forEachLine { line ->
                val parts = line.split("|", limit = 2)
                if (parts.size == 2) {
                    val english = parts[0].trim()
                    val russian = parts[1].trim()
                    addPhraseToDictionary(english, russian)
                }
            }
        }
    }

    private fun addPhraseToDictionary(english: String, russian: String) {
        englishToRussian.getOrPut(english.lowercase(Locale.getDefault())) { mutableSetOf() }.add(russian)
        russianToEnglish.getOrPut(russian) { mutableSetOf() }.add(english.lowercase(Locale.getDefault()))
    }

    fun saveDictionary() {
        if (isModified) {
            dictionaryFile.bufferedWriter().use { writer ->
                englishToRussian.entries.sortedBy { it.key }.forEach { (english, russianTranslations) ->
                    russianTranslations.forEach { russian ->
                        writer.write("$english|$russian")
                        writer.newLine()
                    }
                }
            }
            isModified = false
        }
    }

    fun translate(phrase: String, isEnglishPhrase: Boolean): Set<String> {
        return if (isEnglishPhrase) {
            englishToRussian[phrase.lowercase(Locale.getDefault())] ?: emptySet()
        } else {
            russianToEnglish[phrase] ?: emptySet()
        }
    }

    fun addTranslation(phrase: String, translation: String, isEnglishPhrase: Boolean) {
        if (isEnglishPhrase) {
            addPhraseToDictionary(phrase, translation)
        } else {
            addPhraseToDictionary(translation, phrase)
        }
        isModified = true
    }

    fun hasModifications(): Boolean = isModified
}