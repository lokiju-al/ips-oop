package lab_2.dictionary_2_3_2

import java.io.File
import java.util.*

class Dictionary(private val dictionaryFile: File) {
    private val dictionary = mutableMapOf<String, String>()
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
                }
            }
        }
    }

    fun saveDictionary() {
        if (isModified) {
            dictionaryFile.bufferedWriter().use { writer ->
                dictionary.forEach { (key, value) ->
                    writer.write("$key|$value")
                    writer.newLine()
                }
            }
            isModified = false
        }
    }

    fun translate(englishPhrase: String): String? {
        return dictionary[englishPhrase.lowercase(Locale.getDefault())]
    }

    fun addTranslation(englishPhrase: String, russianTranslation: String) {
        dictionary[englishPhrase.lowercase(Locale.getDefault())] = russianTranslation
        isModified = true
        println("Слово “$englishPhrase” сохранено в словаре как “$russianTranslation”.")
    }

    fun hasModifications(): Boolean = isModified
}