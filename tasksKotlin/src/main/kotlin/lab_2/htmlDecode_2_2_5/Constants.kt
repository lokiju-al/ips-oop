package lab_2.htmlDecode_2_2_5

const val AMPERSAND = '&'
const val APOSTROPHE = '\''
const val GREATER_THAN = '>'
const val INDEX_NOT_FOUND = -1
const val LESS_THAN = '<'
const val QUOTE = '\"'
const val SEMICOLON = ';'

val DECODE_MAP = mapOf(
    "&amp;" to AMPERSAND,
    "&apos;" to APOSTROPHE,
    "&gt;" to GREATER_THAN,
    "&lt;" to LESS_THAN,
    "&quot;" to QUOTE,
)