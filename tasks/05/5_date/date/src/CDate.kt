enum class Month(val value: Int) {
    JANUARY(1), FEBRUARY(2), MARCH(3), APRIL(4),
    MAY(5), JUNE(6), JULY(7), AUGUST(8),
    SEPTEMBER(9), OCTOBER(10), NOVEMBER(11), DECEMBER(12)
}

enum class WeekDay(val value: Int) {
    SUNDAY(0), MONDAY(1), TUESDAY(2), WEDNESDAY(3),
    THURSDAY(4), FRIDAY(5), SATURDAY(6)
}

class CDate {
    private var day: Int
    private var month: Month
    private var year: Int

    constructor(day: Int, month: Month, year: Int) {
        this.day = day
        this.month = month
        this.year = year
    }

    constructor(timestamp: Int) {
        var days = timestamp
        year = 1970
        month = Month.JANUARY
        day = 1

        while (days >= daysInYear(year)) {
            days -= daysInYear(year)
            year++
        }

        for (m in Month.entries) {
            val daysInMonth = daysInMonth(m, year)
            if (days < daysInMonth) {
                month = m
                day = days + 1
                break
            } else {
                days -= daysInMonth
            }
        }
    }

    constructor() : this(1, Month.JANUARY, 1970)

    fun getDay(): Int {
        return day
    }

    fun getMonth(): Month {
        return month
    }

    fun getYear(): Int {
        return year
    }

    fun getWeekDay(): WeekDay {
        val daysFromEpoch = daysFromEpoch()
        return WeekDay.entries[(daysFromEpoch + 4) % 7]
    }

    fun isValid(): Boolean {
        if (year < 1970 || year > 9999) return false
        if (month !in Month.JANUARY..Month.DECEMBER) return false
        if (day < 1 || day > daysInMonth(month, year)) return false
        return true
    }

    private fun isLeapYear(year: Int): Boolean {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)
    }

    private fun daysInMonth(month: Month, year: Int): Int {
        return when (month) {
            Month.JANUARY, Month.MARCH, Month.MAY, Month.JULY,
            Month.AUGUST, Month.OCTOBER, Month.DECEMBER -> 31
            Month.APRIL, Month.JUNE, Month.SEPTEMBER, Month.NOVEMBER -> 30
            Month.FEBRUARY -> if (isLeapYear(year)) 29 else 28
        }
    }

    private fun daysInYear(year: Int): Int {
        return if (isLeapYear(year)) 366 else 365
    }

    private fun daysFromEpoch(): Int {
        var days = 0
        for (y in 1970..<year) {
            days += daysInYear(y)
        }
        for (m in Month.entries) {
            if (m == month) break
            days += daysInMonth(m, year)
        }
        days += day - 1
        return days
    }

    operator fun inc(): CDate {
        val date = CDate(day, month, year)
        date.day++
        if (date.day > daysInMonth(date.month, date.year)) {
            date.day = 1
            date.month = Month.entries.toTypedArray()[(date.month.ordinal + 1) % 12]
            if (date.month == Month.JANUARY) {
                date.year++
            }
        }
        return date
    }

    operator fun dec(): CDate {
        val date = CDate(day, month, year)
        date.day--
        if (date.day < 1) {
            date.month = Month.entries.toTypedArray()[(date.month.ordinal - 1 + 12) % 12]
            date.day = daysInMonth(date.month, date.year)
            if (date.month == Month.DECEMBER) {
                date.year--
            }
        }
        return date
    }
}