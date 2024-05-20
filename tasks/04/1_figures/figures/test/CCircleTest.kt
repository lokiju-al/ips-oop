import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class CCircleTest {
    private var circle = CCircle(CPoint(0.0, 0.0), 0.0, "ffffff", "ffffff")

    @Test
    fun GetAreaTest() {
        assertEquals(0.0, circle.GetArea())

        circle = CCircle(CPoint(0.0, 0.0), 1.0)
        assertEquals(3.141592653589793, circle.GetArea())

        circle = CCircle(CPoint(-60.0, 30.0), 5.32)
        assertEquals(88.91461191895976, circle.GetArea())
    }

    @Test
    fun GetPerimeterTest() {
        circle = CCircle(CPoint(0.0, 0.0), 0.0)
        assertEquals(0.0, circle.GetPerimeter())

        circle = CCircle(CPoint(0.0, 0.0), 1.0)
        assertEquals(6.283185307179586, circle.GetPerimeter())

        circle = CCircle(CPoint(-60.0, 30.0), 5.32)
        assertEquals(33.4265458341954, circle.GetPerimeter())
    }

    @Test
    fun GetOutlineColorTest() {
        circle = CCircle(CPoint(0.0, 0.0), 0.0)
        assertEquals(16777215, circle.GetOutlineColor())

        circle = CCircle(CPoint(0.0, 0.0), 0.0, "000000")
        assertEquals(0, circle.GetOutlineColor())

        circle = CCircle(CPoint(0.0, 0.0), 0.0, "")
        assertEquals(16777215, circle.GetOutlineColor())

        circle = CCircle(CPoint(0.0, 0.0), 0.0, "фывфы")
        assertEquals(16777215, circle.GetOutlineColor())

        circle = CCircle(CPoint(0.0, 0.0), 0.0, "123123")
        assertEquals(1192227, circle.GetOutlineColor())
    }

    @Test
    fun ToStringTest() {
        circle = CCircle(CPoint(0.0, 0.0), 0.0)
        var expectedString = """
            Type: circle
            Center: (0,00, 0,00)
            Radius: 0,00
            Outline Color: 16777215
            Area: 0,00
            Perimeter: 0,00
            
        """.trimIndent()
        assertEquals(expectedString, circle.ToString())

        circle = CCircle(CPoint(-10.012, 3.33), 23.0, "abcdef", "135321")
        expectedString = """
            Type: circle
            Center: (-10,01, 3,33)
            Radius: 23,00
            Outline Color: 11259375
            Fill Color: 1266465
            Area: 1661,90
            Perimeter: 144,51
            
        """.trimIndent()
        assertEquals(expectedString, circle.ToString())
    }

    @Test
    fun GetFillColorTest() {
        circle = CCircle(CPoint(0.0, 0.0), 0.0)
        assertEquals(0, circle.GetFillColor())

        circle = CCircle(CPoint(0.0, 0.0), 0.0, "000000", "ffffff")
        assertEquals(16777215, circle.GetFillColor())

        circle = CCircle(CPoint(0.0, 0.0), 0.0, "", "")
        assertEquals(0, circle.GetFillColor())

        circle = CCircle(CPoint(0.0, 0.0), 0.0, "фывфы", "фывфы")
        assertEquals(0, circle.GetFillColor())

        circle = CCircle(CPoint(0.0, 0.0), 0.0, "123123", "123123")
        assertEquals(1192227, circle.GetFillColor())
    }
}