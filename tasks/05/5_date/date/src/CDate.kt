enum class Month(val value: Int) {
    JANUARY(1), FEBRUARY(2), MARCH(3), APRIL(4),
    MAY(5), JUNE(6), JULY(7), AUGUST(8),
    SEPTEMBER(9), OCTOBER(10), NOVEMBER(11), DECEMBER(12)
}

enum class WeekDay(val value: Int) {
    SUNDAY(0), MONDAY(1), TUESDAY(2), WEDNESDAY(3),
    THURSDAY(4), FRIDAY(5), SATURDAY(6)
}

//переименовать в Date
class CDate {
    private var day: Int
    private var month: Month
    private var year: Int
//лучше хранить timestamp
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
        //не использовать магические числа
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

    //методы не зависящие от состояния объекта должны быть статическими
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

    operator fun plus(days: Int): CDate {
        var resultDay = this.day
        var resultMonth = this.month
        var resultYear = this.year
        var remainingDays = days

        while (remainingDays > 0) {
            val daysInCurrentMonth = daysInMonth(resultMonth, resultYear)
            if (resultDay + remainingDays <= daysInCurrentMonth) {
                resultDay += remainingDays
                remainingDays = 0
            } else {
                remainingDays -= (daysInCurrentMonth - resultDay + 1)
                resultDay = 1
                resultMonth = Month.entries.toTypedArray()[(resultMonth.ordinal + 1) % 12]
                if (resultMonth == Month.JANUARY) {
                    resultYear++
                }
            }
        }

        return CDate(resultDay, resultMonth, resultYear)
    }

    operator fun minus(days: Int): CDate {
        var resultDay = this.day
        var resultMonth = this.month
        var resultYear = this.year
        var remainingDays = days

        while (remainingDays > 0) {
            if (resultDay > remainingDays) {
                resultDay -= remainingDays
                remainingDays = 0
            } else {
                remainingDays -= resultDay
                resultMonth = Month.entries.toTypedArray()[(resultMonth.ordinal - 1 + 12) % 12]
                resultDay = daysInMonth(resultMonth, resultYear)
                if (resultMonth == Month.DECEMBER) {
                    resultYear--
                }
            }
        }

        return CDate(resultDay, resultMonth, resultYear)
    }

    operator fun minus(other: CDate): Int {
        return this.daysFromEpoch() - other.daysFromEpoch()
    }

    fun plusAssign(days: Int) {
        var remainingDays = days

        while (remainingDays > 0) {
            val daysInCurrentMonth = daysInMonth(this.month, this.year)
            if (this.day + remainingDays <= daysInCurrentMonth) {
                this.day += remainingDays
                remainingDays = 0
            } else {
                remainingDays -= (daysInCurrentMonth - this.day + 1)
                this.day = 1
                this.month = Month.entries.toTypedArray()[(this.month.ordinal + 1) % 12]
                if (this.month == Month.JANUARY) {
                    this.year++
                }
            }
        }
    }

    fun minusAssign(days: Int) {
        var remainingDays = days

        while (remainingDays > 0) {
            if (this.day > remainingDays) {
                this.day -= remainingDays
                remainingDays = 0
            } else {
                remainingDays -= this.day
                this.month = Month.entries.toTypedArray()[(this.month.ordinal - 1 + 12) % 12]
                this.day = daysInMonth(this.month, this.year)
                if (this.month == Month.DECEMBER) {
                    this.year--
                }
            }
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is CDate) return false
        return day == other.day && month == other.month && year == other.year
    }

    override fun hashCode(): Int {
        return day.hashCode() + month.hashCode() + year.hashCode()
    }

    operator fun compareTo(other: CDate): Int {
        if (this.year != other.year) {
            return this.year - other.year
        }
        if (this.month != other.month) {
            return this.month.ordinal - other.month.ordinal
        }
        return this.day - other.day
    }
}