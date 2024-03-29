import java.io.File

fun main() {
	val fileName = "C:\\oop-ips\\tasks\\01\\shefer\\shefer.txt"
	val file = File(fileName)

	if (!file.exists()) {
		println("Файл не найден.")
	} else {
		val text = file.readText()
		val newText = text.replace(Regex("[\\r\\n]+"), " ")

		File(fileName).writeText(newText)

		println("Все символы конца строки в файле $fileName были заменены на пробелы.")
	}
}