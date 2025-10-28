package lab_5.date_5_5

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class CDateTest {
    private var date1 = CDate()
    private var date2 = CDate(777)
    private var date3 = CDate(31, Month.DECEMBER, 9999)

    @Test
    fun getDay() {
        assertEquals(1, date1.getDay())
        assertEquals(17, date2.getDay())
        assertEquals(31, date3.getDay())
    }

    @Test
    fun getMonth() {
        assertEquals(Month.JANUARY, date1.getMonth())
        assertEquals(Month.FEBRUARY, date2.getMonth())
        assertEquals(Month.DECEMBER, date3.getMonth())
    }

    @Test
    fun getYear() {
        assertEquals(1970, date1.getYear())
        assertEquals(1972, date2.getYear())
        assertEquals(9999, date3.getYear())
    }

    @Test
    fun getWeekDay() {
        assertEquals(WeekDay.THURSDAY, date1.getWeekDay())
        assertEquals(WeekDay.THURSDAY, date2.getWeekDay())
        assertEquals(WeekDay.FRIDAY, date3.getWeekDay())
    }

    @Test
    fun isValid() {
        assertEquals(true, date1.isValid())
        assertEquals(true, date2.isValid())
        assertEquals(true, date3.isValid())
        assertEquals(false, CDate(-1).isValid())
        assertEquals(false, CDate(1, Month.JANUARY, 10000).isValid())
    }

    @Test
    fun incDec() {
        var date = CDate(2, Month.JANUARY, 1970)

        date++
        assertEquals(3, date.getDay())
        assertEquals(Month.JANUARY, date.getMonth())
        assertEquals(1970, date.getYear())

        ++date
        assertEquals(4, date.getDay())
        assertEquals(Month.JANUARY, date.getMonth())
        assertEquals(1970, date.getYear())

        date--
        assertEquals(3, date.getDay())
        assertEquals(Month.JANUARY, date.getMonth())
        assertEquals(1970, date.getYear())

        --date
        assertEquals(2, date.getDay())
        assertEquals(Month.JANUARY, date.getMonth())
        assertEquals(1970, date.getYear())
    }

    @Test
    fun plusMinus() {
        var date = CDate(4, Month.MAY, 1970)

        date = date + 3
        assertEquals(7, date.getDay())
        assertEquals(Month.MAY, date.getMonth())
        assertEquals(1970, date.getYear())

        date = date - 3
        assertEquals(4, date.getDay())
        assertEquals(Month.MAY, date.getMonth())
        assertEquals(1970, date.getYear())

        date = 3 + date
        assertEquals(7, date.getDay())
        assertEquals(Month.MAY, date.getMonth())
        assertEquals(1970, date.getYear())

        date += 3
        assertEquals(10, date.getDay())
        assertEquals(Month.MAY, date.getMonth())
        assertEquals(1970, date.getYear())

        date -= 3
        assertEquals(7, date.getDay())
        assertEquals(Month.MAY, date.getMonth())
        assertEquals(1970, date.getYear())

        assertEquals(0, date - CDate(7, Month.MAY, 1970))
        assertEquals(-2932896, CDate() - CDate(31, Month.DECEMBER, 9999))
    }

    @Test
    fun comparisonOperators() {
        val date1 = CDate(1, Month.JANUARY, 2010)
        val date2 = CDate(2, Month.JANUARY, 2010)
        val date3 = CDate(1, Month.JANUARY, 2010)

        assertTrue(date1 == date3)
        assertFalse(date1 == date2)

        assertTrue(date1 != date2)
        assertFalse(date1 != date3)

        assertTrue(date1 < date2)
        assertFalse(date2 < date1)

        assertTrue(date2 > date1)
        assertFalse(date1 > date2)

        assertTrue(date1 <= date3)
        assertTrue(date1 <= date2)
        assertFalse(date2 <= date1)

        assertTrue(date2 >= date1)
        assertTrue(date1 >= date3)
        assertFalse(date1 >= date2)
    }
}