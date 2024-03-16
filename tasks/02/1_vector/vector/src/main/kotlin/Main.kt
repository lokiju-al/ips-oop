fun readDoubleArrayFromInput(): DoubleArray {
	print("Input floating point numbers separated by spaces: ")
	val inputLine = readlnOrNull()
	val numbers: List<String>? = inputLine?.split(" ")
	val numberList = mutableListOf<Double>()
	numbers?.forEachIndexed { index, value ->
		try {
			val number = value.toDouble()
			numberList.add(number)
		} catch (e: NumberFormatException) {
			println("Element #${index + 1} \"$value\" isn't a number")
		}
	}
	return numberList.toDoubleArray()
}

//отдельно модификация и отдельно и печать
fun modifyAndPrintArray(array: DoubleArray) {
	val minElement = array.minOrNull()
	print("Modified input string (variant 2): ")
	if (minElement != null) {
		//алгоритм минуя индексы
		for (i in array.indices) {
			array[i] *= minElement
			print("${array[i]} ")
		}
		println()
	}
}

fun sortAndPrintArray(array: DoubleArray) {
	val sortedArray = array.sorted()
	print("String with sorted array (bonus): ")
	for (i in array.indices) {
		print("${sortedArray[i]} ")
	}
}

//другое имя
fun printModifiedInputString() {
	val array = readDoubleArrayFromInput()
	modifyAndPrintArray(array)
	sortAndPrintArray(array)
}

fun main() {
	printModifiedInputString()
}
