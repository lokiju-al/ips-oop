import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class CLineSegmentTest {
    val startPoint = CPoint(0.0, 0.0)
    val endPoint = CPoint(3.0, 4.0)

    @Test
    fun testGetPerimeter() {
        val lineSegment = CLineSegment(startPoint, endPoint)
        val expectedPerimeter = 5.0
        assertEquals(expectedPerimeter, lineSegment.GetPerimeter())
    }

    @Test
    fun testGetOutlineColor() {
        val outlineColor = "ffffff"
        val lineSegment = CLineSegment(startPoint, endPoint, outlineColor)
        val expectedColor: Number = 16777215
        assertEquals(expectedColor, lineSegment.GetOutlineColor())
    }

    @Test
    fun testToString() {
        val outlineColor = "000"
        var lineSegment = CLineSegment(startPoint, endPoint)
        var expectedString = """
            Type: line segment
            Start point: (0.0, 0.0)
            End point: (3.0, 4.0)
            Stroke Color: 16777215
            Area: 0,00
            Perimeter: 5,00
            
        """.trimIndent()
        assertEquals(expectedString, lineSegment.ToString())
        lineSegment = CLineSegment(startPoint, endPoint, outlineColor)
        expectedString = """
            Type: line segment
            Start point: (0.0, 0.0)
            End point: (3.0, 4.0)
            Stroke Color: 0
            Area: 0,00
            Perimeter: 5,00
            
        """.trimIndent()
        assertEquals(expectedString, lineSegment.ToString())
    }
}