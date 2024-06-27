fun main() {
    // Примеры использования:
    val date1 = CDate(3, Month.JANUARY, 1970) // 3 января 1970
    val date2 = CDate(32) // 2 февраля 1970
    val date3 = CDate() // 1 января 1970 по умолчанию

    var dayOfWeek = date1.getWeekDay()
    var isValid = date1.isValid()
    println("Date1: ${date1.getDay()}-${date1.getMonth()}-${date1.getYear()} is valid: $isValid and is ${dayOfWeek.name}")
    dayOfWeek = date2.getWeekDay()
    isValid = date2.isValid()
    println("Date2: ${date2.getDay()}-${date2.getMonth()}-${date2.getYear()} is valid: $isValid and is ${dayOfWeek.name}")
    dayOfWeek = date3.getWeekDay()
    isValid = date3.isValid()
    println("Date3: ${date3.getDay()}-${date3.getMonth()}-${date3.getYear()} is valid: $isValid and is ${dayOfWeek.name}")
}