package lab_2.htmlDecode_2_2_5

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class DecoderTests {
    private var decodedString = "Cat <says> \"Meow\". M&M's"
    private var encodedString = "Cat &lt;says&gt; &quot;Meow&quot;. M&amp;M&apos;s"
    private var undecodableString = "Недекодируемая строка: &lt &gt &amp &quot &apos &&;; &zzz;"
    
    @Test
    fun htmlDecode() {
        Assertions.assertEquals("$AMPERSAND", htmlDecode("&amp;"))
        Assertions.assertEquals("$APOSTROPHE", htmlDecode("&apos;"))
        Assertions.assertEquals("$GREATER_THAN", htmlDecode("&gt;"))
        Assertions.assertEquals("$LESS_THAN", htmlDecode("&lt;"))
        Assertions.assertEquals("$QUOTE", htmlDecode("&quot;"))
        Assertions.assertEquals(decodedString, htmlDecode(encodedString))
        Assertions.assertEquals(undecodableString, htmlDecode(undecodableString))
    }
}