package lab_2.primeNumbers_2_4_4

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Timeout
import java.util.concurrent.TimeUnit

class PrimeNumbersTests {

    @Test
    fun testCountPrimesBasic() {
        assertEquals(0, generatePrimeNumbersSet(0).size)
        assertEquals(0, generatePrimeNumbersSet(1).size)
        assertEquals(1, generatePrimeNumbersSet(2).size)
        assertEquals(4, generatePrimeNumbersSet(10).size)
        assertEquals(25, generatePrimeNumbersSet(100).size)
    }

    @Test
    @Timeout(value = 20, unit = TimeUnit.SECONDS)
    fun testCountPrimesPerformance() {
        val startTime = System.currentTimeMillis()
        val count = generatePrimeNumbersSet(100_000_000).size
        val endTime = System.currentTimeMillis()

        assertEquals(5761455, count)
        assertTrue(
            endTime - startTime < 12000,
            "Подсчет должен выполняться менее чем за 12 секунд"
        )
    }
}