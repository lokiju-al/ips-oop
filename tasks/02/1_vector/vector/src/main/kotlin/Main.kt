fun readIntArrayFromString(input: String): IntArray {
    val numbers = input.split(" ").map { it.toInt() }
    return numbers.toIntArray()
}


fun main() {
    val input = "10 20 30 40 50"
    val array = readIntArrayFromString(input)
    println("Считанный массив чисел: ${array.joinToString()}")
    print("Input floating point numbers separated by spaces: ")
    val name = readlnOrNull()

    println("Modified input string: $name")
}