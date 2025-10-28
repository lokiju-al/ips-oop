package htmlDecode

private fun getNextIndexAndAppendActualChar(inputString: String, currentIndex: Int, decodedString: StringBuilder): Int {
    decodedString.append(inputString[currentIndex])
    return currentIndex + 1
}

private fun getNextIndexAndAppendDecodedCharOrActualChar(inputString: String, currentIndex: Int, decodedString: StringBuilder): Int {
    val semicolonIndex = inputString.indexOf(SEMICOLON, currentIndex)

    if (semicolonIndex != INDEX_NOT_FOUND) {
        val entity = inputString.substring(currentIndex, semicolonIndex + 1)
        val decodedChar = DECODE_MAP[entity]

        if (decodedChar != null) {
            decodedString.append(decodedChar)
            return semicolonIndex + 1
        }
    }

    return getNextIndexAndAppendActualChar(inputString, currentIndex, decodedString)
}

fun htmlDecode(inputString: String): String {
    val decodedString = StringBuilder()
    var i = 0

    while (i < inputString.length) {
        i = if (inputString[i] == AMPERSAND) {
            getNextIndexAndAppendDecodedCharOrActualChar(inputString, i, decodedString)
        } else {
            getNextIndexAndAppendActualChar(inputString, i, decodedString)
        }
    }

    return decodedString.toString()
}