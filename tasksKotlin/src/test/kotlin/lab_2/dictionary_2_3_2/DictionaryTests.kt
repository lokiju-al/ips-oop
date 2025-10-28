package lab_2.dictionary_2_3_2

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.io.TempDir
import java.io.File
import java.nio.file.Path

class DictionaryTests {

    @TempDir
    lateinit var tempDir: Path

    private lateinit var testDictionaryFile: File

    @BeforeEach
    fun setUp() {
        testDictionaryFile = File(tempDir.toFile(), "test_dictionary.txt")
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
        assertEquals("привет", dictionary.translate("hello"))
        assertEquals("мир", dictionary.translate("world"))
        assertEquals("кот", dictionary.translate("cat"))
    }

    @Test
    fun `test translate existing word`() {
        testDictionaryFile.writeText(
            """
            apple|яблоко
            book|книга
        """.trimIndent()
        )

        val dictionary = Dictionary(testDictionaryFile)

        assertEquals("яблоко", dictionary.translate("apple"))
        assertEquals("книга", dictionary.translate("book"))
    }

    @Test
    fun `test translate non-existing word`() {
        testDictionaryFile.writeText("hello|привет")
        val dictionary = Dictionary(testDictionaryFile)

        assertNull(dictionary.translate("nonexistent"))
        assertNull(dictionary.translate("unknown"))
    }

    @Test
    fun `test translate is case insensitive`() {
        testDictionaryFile.writeText("hello|привет")
        val dictionary = Dictionary(testDictionaryFile)

        assertEquals("привет", dictionary.translate("HELLO"))
        assertEquals("привет", dictionary.translate("Hello"))
        assertEquals("привет", dictionary.translate("hello"))
    }

    @Test
    fun `test add translation`() {
        val dictionary = Dictionary(testDictionaryFile)

        dictionary.addTranslation("computer", "компьютер")

        assertEquals("компьютер", dictionary.translate("computer"))
        assertTrue(dictionary.hasModifications())
    }

    @Test
    fun `test add translation is case insensitive`() {
        val dictionary = Dictionary(testDictionaryFile)

        dictionary.addTranslation("COMPUTER", "компьютер")

        assertEquals("компьютер", dictionary.translate("computer"))
        assertEquals("компьютер", dictionary.translate("COMPUTER"))
    }

    @Test
    fun `test hasModifications returns false initially`() {
        val dictionary = Dictionary(testDictionaryFile)

        assertFalse(dictionary.hasModifications())
    }

    @Test
    fun `test hasModifications returns true after adding translation`() {
        val dictionary = Dictionary(testDictionaryFile)

        dictionary.addTranslation("test", "тест")

        assertTrue(dictionary.hasModifications())
    }

    @Test
    fun `test save dictionary creates file`() {
        val dictionary = Dictionary(testDictionaryFile)

        dictionary.addTranslation("hello", "привет")
        dictionary.addTranslation("world", "мир")

        dictionary.saveDictionary()

        assertTrue(testDictionaryFile.exists())
        val fileContent = testDictionaryFile.readText()
        assertTrue(fileContent.contains("hello|привет"))
        assertTrue(fileContent.contains("world|мир"))
        assertFalse(dictionary.hasModifications())
    }

    @Test
    fun `test save dictionary after modifications`() {
        testDictionaryFile.writeText("hello|привет")
        val dictionary = Dictionary(testDictionaryFile)

        dictionary.addTranslation("new", "новый")
        dictionary.saveDictionary()

        val fileContent = testDictionaryFile.readText()
        assertTrue(fileContent.contains("hello|привет"))
        assertTrue(fileContent.contains("new|новый"))
        assertFalse(dictionary.hasModifications())
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
    fun `test multiple translations`() {
        val dictionary = Dictionary(testDictionaryFile)

        dictionary.addTranslation("one", "один")
        dictionary.addTranslation("two", "два")
        dictionary.addTranslation("three", "три")

        assertEquals("один", dictionary.translate("one"))
        assertEquals("два", dictionary.translate("two"))
        assertEquals("три", dictionary.translate("three"))
    }

    @Test
    fun `test overwrite existing translation`() {
        testDictionaryFile.writeText("hello|привет")
        val dictionary = Dictionary(testDictionaryFile)

        dictionary.addTranslation("hello", "здравствуйте")

        assertEquals("здравствуйте", dictionary.translate("hello"))
        assertTrue(dictionary.hasModifications())
    }

    @Test
    fun `test dictionary with phrases`() {
        val dictionary = Dictionary(testDictionaryFile)

        dictionary.addTranslation("good morning", "доброе утро")
        dictionary.addTranslation("thank you", "спасибо")

        assertEquals("доброе утро", dictionary.translate("good morning"))
        assertEquals("спасибо", dictionary.translate("thank you"))
    }

    @Test
    fun `test empty dictionary file`() {
        testDictionaryFile.writeText("")
        val dictionary = Dictionary(testDictionaryFile)

        assertNull(dictionary.translate("any"))
        assertFalse(dictionary.hasModifications())
    }
}