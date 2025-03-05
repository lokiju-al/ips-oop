fun htmlEncode(text: String): String {
    var encoded = ""
    for (char in text) {
        encoded += when (char) {
            '\"' -> "&quot;"
            '\'' -> "&apos;"
            '<' -> "&lt;"
            '>' -> "&gt;"
            '&' -> "&amp;"
            else -> char.toString()
        }
    }
    return encoded
}

fun htmlDecode(input: String): String {
    val htmlEntities = mapOf(
        "&quot;" to '"',
        "&apos;" to '\'',
        "&lt;" to '<',
        "&gt;" to '>',
        "&amp;" to '&'
    )

    val result = StringBuilder()
    var i = 0

    while (i < input.length) {
        if (input[i] == '&') {
            val semicolon = input.indexOf(';', i)
            if (semicolon != -1) {
                val entity = input.substring(i, semicolon + 1)
                val decodedChar = htmlEntities[entity]
                if (decodedChar != null) {
                    result.append(decodedChar)
                    i = semicolon + 1
                    continue
                }
            }
        }
        result.append(input[i])
        i++
    }

    return result.toString()
}

fun main() {
    val input = """
        Это пример текста с особыми символами: < > & " '
    """.trimIndent()

    val encodedOutput = htmlEncode(input)
    println(encodedOutput)
    println("Введите текст с HTML-сущностями. Для завершения введите 'exit':")
    while (true) {
        val line = readLine() ?: break
        if (line.equals("exit", ignoreCase = true)) break
        val decodedLine = htmlDecode(line)
        println(decodedLine)
    }
}