import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class CTriangleTest {
    var triangle = CTriangle(CPoint(0.0, 0.0), CPoint(0.0, 0.0), CPoint(0.0, 0.0), "ffffff", "ffffff")

    @Test
    fun GetAreaTest() {
        assertEquals(0.0, triangle.GetArea())

        triangle = CTriangle(CPoint(0.0, 0.0), CPoint(1.0, 1.0), CPoint(2.0, 2.0))
        assertEquals(0.0, triangle.GetArea())

        triangle = CTriangle(CPoint(-10.0, 12.0), CPoint(2.0, 3.0), CPoint(1.0, 6.0))
        assertEquals(13.499999999999986, triangle.GetArea())
    }

    @Test
    fun GetPerimeterTest() {
        triangle = CTriangle(CPoint(0.0, 0.0), CPoint(0.0, 0.0), CPoint(0.0, 0.0))
        assertEquals(0.0, triangle.GetPerimeter())

        triangle = CTriangle(CPoint(-10.0, 12.0), CPoint(2.0, 3.0), CPoint(1.0, 6.0))
        assertEquals(30.692241746310046, triangle.GetPerimeter())
    }

    @Test
    fun GetOutlineColorTest() {
        triangle = CTriangle(CPoint(0.0, 0.0), CPoint(2.0, 3.0), CPoint(1.0, 6.0))
        assertEquals(16777215, triangle.GetOutlineColor())

        triangle = CTriangle(CPoint(0.0, 0.0), CPoint(2.0, 3.0), CPoint(1.0, 6.0), "000000")
        assertEquals(0, triangle.GetOutlineColor())

        triangle = CTriangle(CPoint(0.0, 0.0), CPoint(2.0, 3.0), CPoint(1.0, 6.0), "")
        assertEquals(16777215, triangle.GetOutlineColor())

        triangle = CTriangle(CPoint(0.0, 0.0), CPoint(2.0, 3.0), CPoint(1.0, 6.0), "фывфы")
        assertEquals(16777215, triangle.GetOutlineColor())

        triangle = CTriangle(CPoint(0.0, 0.0), CPoint(2.0, 3.0), CPoint(1.0, 6.0), "123123")
        assertEquals(1192227, triangle.GetOutlineColor())
    }

    @Test
    fun ToStringTest() {
        triangle = CTriangle(CPoint(0.0, 0.0), CPoint(2.0, 3.0), CPoint(1.0, 6.0))
        var expectedString = """
            Type: triangle
            Vertex 1: (0.0, 0.0)
            Vertex 2: (2.0, 3.0)
            Vertex 3: (1.0, 6.0)
            Outline Color: 16777215
            Area: 4,50
            Perimeter: 12,85
            
        """.trimIndent()
        assertEquals(expectedString, triangle.ToString())

        triangle = CTriangle(CPoint(5.6, 1.8), CPoint(-3.4, 12.0), CPoint(16.0, 6.0), "abcdef")
        expectedString = """
            Type: triangle
            Vertex 1: (5.6, 1.8)
            Vertex 2: (-3.4, 12.0)
            Vertex 3: (16.0, 6.0)
            Outline Color: 11259375
            Area: 71,94
            Perimeter: 45,13
            
        """.trimIndent()
        assertEquals(expectedString, triangle.ToString())
    }
}