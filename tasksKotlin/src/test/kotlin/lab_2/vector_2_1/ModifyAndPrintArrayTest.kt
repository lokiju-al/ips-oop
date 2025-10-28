package lab_2.vector_2_1

import org.junit.jupiter.api.Assertions.assertArrayEquals
import org.junit.jupiter.api.Test

class ArrayModifierTest {

	@Test
	fun testModifyAndPrintArray() {
		val array = doubleArrayOf(4.0, 8.0, 2.0, 6.0)
		val expectedModifiedArray = doubleArrayOf(8.0, 16.0, 4.0, 12.0)

		modifyAndPrintArray(array)

		assertArrayEquals(expectedModifiedArray, array, 0.001)
	}

	@Test
	fun testModifyAndPrintArrayWithEmptyArray() {
		val array = doubleArrayOf()
		val expectedModifiedArray = doubleArrayOf()

		modifyAndPrintArray(array)

		assertArrayEquals(expectedModifiedArray, array, 0.001)
	}

	@Test
	fun testModifyAndPrintArrayWithAllZeroes() {
		val array = doubleArrayOf(0.0)
		val expectedModifiedArray = doubleArrayOf(0.0)

		modifyAndPrintArray(array)

		assertArrayEquals(expectedModifiedArray, array, 0.001)
	}
}