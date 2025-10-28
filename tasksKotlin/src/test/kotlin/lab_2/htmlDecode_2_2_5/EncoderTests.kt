package lab_2.htmlDecode_2_2_5

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class EncoderTests {
    @Test
    fun htmlEncode() {
        assertEquals("&amp;", htmlEncode("$AMPERSAND"))
        assertEquals("&apos;", htmlEncode("$APOSTROPHE"))
        assertEquals("&gt;", htmlEncode("$GREATER_THAN"))
        assertEquals("&lt;", htmlEncode("$LESS_THAN"))
        assertEquals("&quot;", htmlEncode("$QUOTE"))
    }
}