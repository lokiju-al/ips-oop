import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.io.ByteArrayOutputStream
import java.io.PrintStream

class ArraySorterTest {
	@Test
	fun testSortAndPrintArray() {
		val array = doubleArrayOf(4.0, 8.0, 2.0, 6.0)
		val expectedSortedString = "String with sorted array (bonus): 2.0 4.0 6.0 8.0 "

		val outputStream = ByteArrayOutputStream()
		System.setOut(PrintStream(outputStream))

		sortAndPrintArray(array)

		assertEquals(expectedSortedString, outputStream.toString())
	}

	@Test
	fun testSortAndPrintArrayWithEmptyArray() {
		val array = doubleArrayOf()
		val expectedSortedString = "String with sorted array (bonus): "

		val outputStream = ByteArrayOutputStream()
		System.setOut(PrintStream(outputStream))

		sortAndPrintArray(array)

		assertEquals(expectedSortedString, outputStream.toString())
	}

	@Test
	fun testSortAndPrintArrayWithSingleElement() {
		val array = doubleArrayOf(5.0)
		val expectedSortedString = "String with sorted array (bonus): 5.0 "

		val outputStream = ByteArrayOutputStream()
		System.setOut(PrintStream(outputStream))

		sortAndPrintArray(array)

		assertEquals(expectedSortedString, outputStream.toString())
	}
}
