package lab_2.htmlDecode_2_2_5

fun htmlEncode(inputString: String): String {
    val encodedString = StringBuilder()

    for (char in inputString) {
        when (char) {
            AMPERSAND -> encodedString.append("&amp;")
            APOSTROPHE -> encodedString.append("&apos;")
            GREATER_THAN -> encodedString.append("&gt;")
            LESS_THAN -> encodedString.append("&lt;")
            QUOTE -> encodedString.append("&quot;")
            else -> encodedString.append(char)
        }
    }

    return encodedString.toString()
}