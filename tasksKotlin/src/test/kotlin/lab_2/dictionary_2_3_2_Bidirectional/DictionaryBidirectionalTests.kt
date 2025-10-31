package lab_2.dictionary_2_3_2_Bidirectional

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.io.TempDir
import java.io.File
import java.nio.file.Path

class DictionaryBidirectionalTests {

    @TempDir
    lateinit var tempDir: Path

    private lateinit var testDictionaryFile: File

    @BeforeEach
    fun setUp() {
        testDictionaryFile = File(tempDir.toFile(), "test_dictionary.txt")
    }

    @Test
    fun `test hasModifications returns false initially`() {
        val dictionary = Dictionary(testDictionaryFile)

        Assertions.assertFalse(dictionary.hasModifications())
    }

    @Test
    fun `test hasModifications returns true after adding translation`() {
        val dictionary = Dictionary(testDictionaryFile)

        dictionary.addTranslation("test", "тест", true)

        assertTrue(dictionary.hasModifications())
    }

    @Test
    fun `test dictionary initialization with existing file`() {
        testDictionaryFile.writeText(
            """
            hello|привет
            world|мир
            cat|кот
        """.trimIndent()
        )

        val dictionary = Dictionary(testDictionaryFile)

        assertTrue(testDictionaryFile.exists())
        assertEquals(setOf("привет"), dictionary.translate("hello", true))
        assertEquals(setOf("мир"), dictionary.translate("world", true))
        assertEquals(setOf("кот"), dictionary.translate("cat", true))
        assertEquals(setOf("hello"), dictionary.translate("привет", false))
        assertEquals(setOf("world"), dictionary.translate("мир", false))
        assertEquals(setOf("cat"), dictionary.translate("кот", false))
    }

    @Test
    fun `test translate non-existing word`() {
        testDictionaryFile.writeText("hello|привет")
        val dictionary = Dictionary(testDictionaryFile)

        assertEquals(emptySet<String>(), dictionary.translate("nonexistent", true))
        assertEquals(emptySet<String>(), dictionary.translate("неизвестный", false))
    }

    @Test
    fun `test multiple translations for same word`() {
        val dictionary = Dictionary(testDictionaryFile)

        dictionary.addTranslation("cat", "кошка", true)
        dictionary.addTranslation("cat", "кот", true)

        val translations = dictionary.translate("cat", true)
        assertEquals(2, translations.size)
        assertTrue(translations.contains("кошка"))
        assertTrue(translations.contains("кот"))
    }

    @Test
    fun `test reverse translation with multiple options`() {
        val dictionary = Dictionary(testDictionaryFile)

        dictionary.addTranslation("cat", "кошка", true)
        dictionary.addTranslation("cat", "кот", true)
        dictionary.addTranslation("dog", "собака", true)

        val catTranslations = dictionary.translate("кошка", false)
        assertEquals(1, catTranslations.size)
        assertTrue(catTranslations.contains("cat"))

        val dogTranslations = dictionary.translate("собака", false)
        assertEquals(1, dogTranslations.size)
        assertTrue(dogTranslations.contains("dog"))
    }

    @Test
    fun `test bidirectional translation`() {
        val dictionary = Dictionary(testDictionaryFile)

        dictionary.addTranslation("hello", "привет", true)
        dictionary.addTranslation("hello", "здравствуйте", true)

        val enToRu = dictionary.translate("hello", true)
        assertEquals(2, enToRu.size)
        assertTrue(enToRu.contains("привет"))
        assertTrue(enToRu.contains("здравствуйте"))

        val ruToEn1 = dictionary.translate("привет", false)
        assertEquals(1, ruToEn1.size)
        assertTrue(ruToEn1.contains("hello"))

        val ruToEn2 = dictionary.translate("здравствуйте", false)
        assertEquals(1, ruToEn2.size)
        assertTrue(ruToEn2.contains("hello"))
    }

    @Test
    fun `test case insensitive english words`() {
        val dictionary = Dictionary(testDictionaryFile)

        dictionary.addTranslation("HELLO", "привет", true)

        assertEquals(setOf("привет"), dictionary.translate("hello", true))
        assertEquals(setOf("привет"), dictionary.translate("HELLO", true))
        assertEquals(setOf("привет"), dictionary.translate("Hello", true))
    }

    @Test
    fun `test case sensitive russian words`() {
        val dictionary = Dictionary(testDictionaryFile)

        dictionary.addTranslation("hello", "Привет", true)
        dictionary.addTranslation("hi", "привет", true)

        assertEquals(setOf("hello"), dictionary.translate("Привет", false))
        assertEquals(setOf("hi"), dictionary.translate("привет", false))
        assertTrue(dictionary.translate("ПРИВЕТ", false).isEmpty())
    }

    @Test
    fun `test save and load multiple translations`() {
        val dictionary = Dictionary(testDictionaryFile)

        dictionary.addTranslation("cat", "кошка", true)
        dictionary.addTranslation("cat", "кот", true)
        dictionary.addTranslation("mouse", "мышь", true)

        dictionary.saveDictionary()

        val loadedDictionary = Dictionary(testDictionaryFile)

        val catTranslations = loadedDictionary.translate("cat", true)
        assertEquals(2, catTranslations.size)
        assertTrue(catTranslations.contains("кошка"))
        assertTrue(catTranslations.contains("кот"))

        val mouseTranslations = loadedDictionary.translate("mouse", true)
        assertEquals(1, mouseTranslations.size)
        assertTrue(mouseTranslations.contains("мышь"))
    }

    @Test
    fun `test complex phrases`() {
        val dictionary = Dictionary(testDictionaryFile)

        dictionary.addTranslation("good morning", "доброе утро", true)
        dictionary.addTranslation("thank you", "спасибо", true)
        dictionary.addTranslation("good morning", "утро доброе", true)

        val translations = dictionary.translate("good morning", true)
        assertEquals(2, translations.size)
        assertTrue(translations.contains("доброе утро"))
        assertTrue(translations.contains("утро доброе"))

        val reverseTranslations = dictionary.translate("доброе утро", false)
        assertEquals(1, reverseTranslations.size)
        assertTrue(reverseTranslations.contains("good morning"))
    }

    @Test
    fun `test save dictionary creates file`() {
        val dictionary = Dictionary(testDictionaryFile)

        dictionary.addTranslation("hello", "привет", true)
        dictionary.addTranslation("world", "мир", true)

        dictionary.saveDictionary()

        assertTrue(testDictionaryFile.exists())
        val fileContent = testDictionaryFile.readText()
        assertTrue(fileContent.contains("hello|привет"))
        assertTrue(fileContent.contains("world|мир"))
        Assertions.assertFalse(dictionary.hasModifications())
    }

    @Test
    fun `test save dictionary after modifications`() {
        testDictionaryFile.writeText("hello|привет")
        val dictionary = Dictionary(testDictionaryFile)

        dictionary.addTranslation("new", "новый", true)
        dictionary.saveDictionary()

        val fileContent = testDictionaryFile.readText()
        assertTrue(fileContent.contains("hello|привет"))
        assertTrue(fileContent.contains("new|новый"))
        Assertions.assertFalse(dictionary.hasModifications())
    }

    @Test
    fun `test save dictionary without modifications`() {
        testDictionaryFile.writeText("hello|привет")
        val dictionary = Dictionary(testDictionaryFile)

        dictionary.saveDictionary()

        val fileContent = testDictionaryFile.readText()
        assertEquals("hello|привет", fileContent.trim())
    }

    @Test
    fun `test empty dictionary file`() {
        testDictionaryFile.writeText("")
        val dictionary = Dictionary(testDictionaryFile)

        assertEquals(emptySet<String>(), dictionary.translate("any", true))
        Assertions.assertFalse(dictionary.hasModifications())
    }
}