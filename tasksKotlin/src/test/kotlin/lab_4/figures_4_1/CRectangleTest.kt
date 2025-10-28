package lab_4.figures_4_1

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class CRectangleTest {
    private var rectangle = CRectangle(CPoint(0.0, 0.0), 0.0, 0.0, "ffffff", "ffffff")

    @Test
    fun GetAreaTest() {
        assertEquals(0.0, rectangle.GetArea())

        rectangle = CRectangle(CPoint(0.0, 0.0), 1.0, 12.0)
        assertEquals(12.0, rectangle.GetArea())

        rectangle = CRectangle(CPoint(-60.0, 30.0), 5.32, 2.456)
        assertEquals(13.06592, rectangle.GetArea())
    }

    @Test
    fun GetPerimeterTest() {
        rectangle = CRectangle(CPoint(0.0, 0.0), 0.0, 0.0)
        assertEquals(0.0, rectangle.GetPerimeter())

        rectangle = CRectangle(CPoint(0.0, 0.0), 1.0, 12.0)
        assertEquals(26.0, rectangle.GetPerimeter())

        rectangle = CRectangle(CPoint(-60.0, 30.0), 5.32, 2.456)
        assertEquals(15.552, rectangle.GetPerimeter())
    }

    @Test
    fun GetOutlineColorTest() {
        rectangle = CRectangle(CPoint(0.0, 0.0), 0.0, 0.0)
        assertEquals(16777215, rectangle.GetOutlineColor())

        rectangle = CRectangle(CPoint(0.0, 0.0), 0.0, 0.0, "000000")
        assertEquals(0, rectangle.GetOutlineColor())

        rectangle = CRectangle(CPoint(0.0, 0.0), 0.0, 0.0, "")
        assertEquals(16777215, rectangle.GetOutlineColor())

        rectangle = CRectangle(CPoint(0.0, 0.0), 0.0, 0.0, "фывфы")
        assertEquals(16777215, rectangle.GetOutlineColor())

        rectangle = CRectangle(CPoint(0.0, 0.0), 0.0, 0.0, "123123")
        assertEquals(1192227, rectangle.GetOutlineColor())
    }

    @Test
    fun ToStringTest() {
        rectangle = CRectangle(CPoint(0.0, 0.0), 0.0, 0.0)
        var expectedString = """
            Type: rectangle
            Left top: (0,00, 0,00)
            Right bottom: (0,00, 0,00)
            Width: 0,00
            Height: 0,00
            Outline Color: 16777215
            Area: 0,00
            Perimeter: 0,00
            
        """.trimIndent()
        assertEquals(expectedString, rectangle.ToString())

        rectangle = CRectangle(CPoint(-10.012, 3.33), 23.0, 1234.1234, "abcdef", "135321")
        expectedString = """
            Type: rectangle
            Left top: (-10,01, 3,33)
            Right bottom: (12,99, 1237,45)
            Width: 23,00
            Height: 1234,12
            Outline Color: 11259375
            Fill Color: 1266465
            Area: 28384,84
            Perimeter: 2514,25
            
        """.trimIndent()
        assertEquals(expectedString, rectangle.ToString())
    }

    @Test
    fun GetFillColorTest() {
        rectangle = CRectangle(CPoint(0.0, 0.0), 0.0, 0.0)
        assertEquals(0, rectangle.GetFillColor())

        rectangle = CRectangle(CPoint(0.0, 0.0), 0.0, 0.0, "000000", "ffffff")
        assertEquals(16777215, rectangle.GetFillColor())

        rectangle = CRectangle(CPoint(0.0, 0.0), 0.0, 0.0, "", "")
        assertEquals(0, rectangle.GetFillColor())

        rectangle = CRectangle(CPoint(0.0, 0.0), 0.0, 0.0, "фывфы", "фывфы")
        assertEquals(0, rectangle.GetFillColor())

        rectangle = CRectangle(CPoint(0.0, 0.0), 0.0, 0.0, "123123", "123123")
        assertEquals(1192227, rectangle.GetFillColor())
    }
}