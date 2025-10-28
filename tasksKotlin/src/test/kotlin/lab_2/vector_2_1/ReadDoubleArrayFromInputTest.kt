package lab_2.vector_2_1

import org.junit.jupiter.api.Assertions.assertArrayEquals
import org.junit.jupiter.api.Test
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.PrintStream

class InputReaderTest {

	@Test
	fun testReadDoubleArrayFromInput() {
		val input = "1.5 2.3 4.6"
		System.setIn(ByteArrayInputStream(input.toByteArray()))
		val output = ByteArrayOutputStream()
		System.setOut(PrintStream(output))

		val expectedArray = doubleArrayOf(1.5, 2.3, 4.6)
		val actualArray = readDoubleArrayFromInput()

		assertArrayEquals(expectedArray, actualArray, 0.001)
	}

	@Test
	fun testReadDoubleArrayFromInputWithInvalidInput() {
		val input = "1.5 abc 4.6"
		System.setIn(ByteArrayInputStream(input.toByteArray()))
		val output = ByteArrayOutputStream()
		System.setOut(PrintStream(output))

		val expectedArray = doubleArrayOf(1.5, 4.6)
		val actualArray = readDoubleArrayFromInput()

		assertArrayEquals(expectedArray, actualArray, 0.001)
		assert(output.toString().contains("Element #2 \"abc\" isn't a number"))
	}

	@Test
	fun testReadDoubleArrayFromInputWithEmptyInput() {
		val input = ""
		System.setIn(ByteArrayInputStream(input.toByteArray()))
		val output = ByteArrayOutputStream()
		System.setOut(PrintStream(output))

		val expectedArray = doubleArrayOf()
		val actualArray = readDoubleArrayFromInput()

		assertArrayEquals(expectedArray, actualArray, 0.001)
	}
}
