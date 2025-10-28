package lab_2.htmlDecode_2_2_5

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class EncoderDecoderTests {
    private var decodedString = "Cat <says> \"Meow\". M&M's"
    private var encodedString = "Cat &lt;says&gt; &quot;Meow&quot;. M&amp;M&apos;s"
    private var undecodableString = "Недекодируемая строка: &lt &gt &amp &quot &apos &&;; &zzz;"

    @Test
    fun htmlEncode() {
        assertEquals("&amp;", htmlEncode("$AMPERSAND"))
        assertEquals("&apos;", htmlEncode("$APOSTROPHE"))
        assertEquals("&gt;", htmlEncode("$GREATER_THAN"))
        assertEquals("&lt;", htmlEncode("$LESS_THAN"))
        assertEquals("&quot;", htmlEncode("$QUOTE"))
        assertEquals(encodedString, htmlEncode(decodedString))
    }

    @Test
    fun htmlDecode() {
        assertEquals("$AMPERSAND", htmlDecode("&amp;"))
        assertEquals("$APOSTROPHE", htmlDecode("&apos;"))
        assertEquals("$GREATER_THAN", htmlDecode("&gt;"))
        assertEquals("$LESS_THAN", htmlDecode("&lt;"))
        assertEquals("$QUOTE", htmlDecode("&quot;"))
        assertEquals(decodedString, htmlDecode(encodedString))
        assertEquals(undecodableString, htmlDecode(undecodableString))
    }
}