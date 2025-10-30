package lab_2.dictionary_2_3_2_Bidirectional

import java.io.File
import java.util.*

class Dictionary(private val dictionaryFile: File) {
    private val dictionary = mutableMapOf<String, String>()
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
                    dictionary[parts[0].trim()] = parts[1].trim()
                    val english = parts[0].trim().lowercase(Locale.getDefault())
                    val russian = parts[1].trim()
                    addTranslationInternal(english, russian)
                }
            }
        }
    }

    private fun addTranslationInternal(english: String, russian: String) {
        englishToRussian.getOrPut(english) { mutableSetOf() }.add(russian)
        russianToEnglish.getOrPut(russian) { mutableSetOf() }.add(english)
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
            russianToEnglish[phrase.lowercase(Locale.getDefault())] ?: emptySet()
        }
    }

    fun addTranslation(phrase: String, translation: String) {
        addTranslationInternal(phrase.lowercase(Locale.getDefault()), translation)
        isModified = true
    }

    fun hasModifications(): Boolean = isModified
}