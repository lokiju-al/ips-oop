import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class CLineSegmentTest {
    var lineSegment = CLineSegment(CPoint(0.0, 0.0), CPoint(3.0, 4.0))
    @Test
    fun GetPerimeterTest() {
        assertEquals(5.0, lineSegment.GetPerimeter())

        lineSegment = CLineSegment(CPoint(2.7, 3.4), CPoint(3.0, 4.0))
        assertEquals(0.6708203932499369, lineSegment.GetPerimeter())

        lineSegment = CLineSegment(CPoint(0.0, 0.0), CPoint(0.0, 0.0))
        assertEquals(0.0, lineSegment.GetPerimeter())
    }

    @Test
    fun GetOutlineColorTest() {
        lineSegment = CLineSegment(CPoint(0.0, 0.0), CPoint(3.0, 4.0), "ffffff")
        assertEquals(16777215, lineSegment.GetOutlineColor())

        lineSegment = CLineSegment(CPoint(0.0, 0.0), CPoint(3.0, 4.0), "000000")
        assertEquals(0, lineSegment.GetOutlineColor())

        lineSegment = CLineSegment(CPoint(0.0, 0.0), CPoint(3.0, 4.0), "")
        assertEquals(16777215, lineSegment.GetOutlineColor())

        lineSegment = CLineSegment(CPoint(0.0, 0.0), CPoint(3.0, 4.0), "фывфы")
        assertEquals(16777215, lineSegment.GetOutlineColor())

        lineSegment = CLineSegment(CPoint(0.0, 0.0), CPoint(3.0, 4.0), "123123")
        assertEquals(1192227, lineSegment.GetOutlineColor())
    }

    @Test
    fun ToStringTest() {
        lineSegment = CLineSegment(CPoint(0.0, 0.0), CPoint(3.0, 4.0))
        var expectedString = """
            Type: line segment
            Start point: (0.0, 0.0)
            End point: (3.0, 4.0)
            Outline Color: 16777215
            Area: 0,00
            Perimeter: 5,00
            
        """.trimIndent()
        assertEquals(expectedString, lineSegment.ToString())

        lineSegment = CLineSegment(CPoint(2.7, 3.4), CPoint(3.0, 4.0), "000000")
        expectedString = """
            Type: line segment
            Start point: (2.7, 3.4)
            End point: (3.0, 4.0)
            Outline Color: 0
            Area: 0,00
            Perimeter: 0,67
            
        """.trimIndent()
        assertEquals(expectedString, lineSegment.ToString())
    }
}