fun readDateFromConsole(): CDate {
    val input = readlnOrNull()?.trim() ?: throw IllegalArgumentException("Некорректная дата")

    return try {
        if (input.contains('.')) {
            val parts = input.split('.')
            val day = parts[0].toInt()
            val month = Month.entries[parts[1].toInt() - 1]
            val year = parts[2].toInt()
            CDate(day, month, year)
        } else if (input === "") {
            CDate()
        } else {
            val timestamp = input.toInt()
            CDate(timestamp)
        }
    } catch (e: Exception) {
        println("Некорректная дата")
        return CDate(0, Month.JANUARY, 0)
    }
}

fun printDate(date: CDate) {
    println("Дата: ${date.getDay()}-${date.getMonth()}-${date.getYear()} (${date.getWeekDay().name})")
}

fun main() {
    println("Введите дату в формате ДД.ММ.ГГГГ, количество дней с начала эпохи или пробел для даты начала эпохи")
    while (true) {
        var date = readDateFromConsole()
        var date2 = readDateFromConsole()

        if (!date.isValid()) {
            printDate(date)
            break
        }

        printDate(date)
        var prefixDate = --date
        printDate(prefixDate)
        printDate(date)

        println()
        printDate(date2)
        var postfixDate = date2--
        printDate(postfixDate)
        printDate(date2)

        println()
        printDate(date)
        prefixDate = ++date
        printDate(prefixDate)
        printDate(date)

        println()
        printDate(date2)
        postfixDate = date2++
        printDate(postfixDate)
        printDate(date2)
    }
}