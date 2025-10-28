package lab_5.date_5_5

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
        return CDate(-1)
    }
}

fun getDateInfo(date: CDate): String {
    return if (date.isValid()) {
        "${date.getDay().toString().padStart(2, '0')}.${
            date.getMonth().value.toString().padStart(2, '0')
        }.${date.getYear()}"
    } else {
        "INVALID"
    }
}

fun getDayInfo(date: CDate): String {
    return if (date.isValid()) {
        date.getWeekDay().name
    } else {
        "INVALID"
    }
}

operator fun Int.plus(date: CDate): CDate {
    return date + this
}

fun main() {
    println("Даты можно вводить любым из форматов: ДД.ММ.ГГГГ, количество дней с начала эпохи, пробел (для даты начала эпохи)")
    print("Введите дату: ")
    var date1 = readDateFromConsole()
    println("Дата1: ${getDateInfo(date1)}, день недели: ${getDayInfo(date1)}")
    println("${getDateInfo(date1)} + 3 = ${getDateInfo((date1 + 3))}")
    println("3 + ${getDateInfo(date1)} = ${getDateInfo((3 + date1))}")
    println("${getDateInfo(date1)} - 3 = ${getDateInfo((date1 - 3))}")
    var date2 = date1
    date1 += 3
    println("(${getDateInfo(date2)} += 3) = ${getDateInfo((date1))}")
    date1 = date2
    date1 -= 3
    println("(${getDateInfo(date2)} -= 3) = ${getDateInfo((date1))}")
    date1 = date2
    date2 = date1--
    println("((${getDateInfo(date2)})--) = ${getDateInfo(date2)}")
    println("(${getDateInfo(date2)})-- = ${getDateInfo(date1)}")
    date1 = date2
    println("--(${getDateInfo(date2)}) = ${getDateInfo(--date1)}")
    date1 = date2
    date2 = date1++
    println("((${getDateInfo(date2)})++) = ${getDateInfo(date2)}")
    println("(${getDateInfo(date2)})++ = ${getDateInfo(date1)}")
    date1 = date2
    println("++(${getDateInfo(date2)}) = ${getDateInfo(++date1)}")
    date1 = date2
    print("Введите вторую дату: ")
    date2 = readDateFromConsole()
    println("Дата2: ${getDateInfo(date2)}, день недели: ${getDayInfo(date2)}")
    println("${getDateInfo(date1)} - ${getDateInfo(date2)} = ${(date1 - date2)}")
    println("${getDateInfo(date1)} == ${getDateInfo(date2)} => ${date1 == date2}")
    println("${getDateInfo(date1)} != ${getDateInfo(date2)} => ${date1 != date2}")
    println("${getDateInfo(date1)} >= ${getDateInfo(date2)} => ${date1 >= date2}")
    println("${getDateInfo(date1)} <= ${getDateInfo(date2)} => ${date1 <= date2}")
    println("${getDateInfo(date1)} > ${getDateInfo(date2)} => ${date1 > date2}")
    println("${getDateInfo(date1)} < ${getDateInfo(date2)} => ${date1 < date2}")
}