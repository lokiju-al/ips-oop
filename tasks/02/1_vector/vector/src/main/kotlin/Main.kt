fun readIntArrayFromInput(): IntArray {
    // Считываем строку из стандартного потока ввода
    val inputLine = readLine()

    // Разбиваем строку на отдельные числа, используя пробел в качестве разделителя
    val numbers = inputLine?.split(" ")

    // Создаем массив чисел
    val intArray = IntArray(numbers?.size ?: 0)

    // Преобразуем строки в числа и заполняем массив
    for (i in numbers!!.indices) {
        intArray[i] = numbers[i].toInt()
    }

    return intArray
}

fun main() {
    print("Input floating point numbers separated by spaces: ")
    val array = readIntArrayFromInput()

    println("Modified input string: ")
    array.forEach { println(it) }
}
