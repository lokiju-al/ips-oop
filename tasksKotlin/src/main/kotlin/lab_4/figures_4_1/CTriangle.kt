package lab_4.figures_4_1

import kotlin.math.sqrt

class CTriangle(
    private val vertex1: CPoint,
    private val vertex2: CPoint,
    private val vertex3: CPoint,
    private val outlineColor: String? = "ffffff",
    private val fillColor: String? = null,
) : ISolidShape {
    override fun GetArea(): Double {
        val side1 = CLineSegment(vertex1, vertex2).GetPerimeter()
        val side2 = CLineSegment(vertex2, vertex3).GetPerimeter()
        val side3 = CLineSegment(vertex3, vertex1).GetPerimeter()
        val s = (side1 + side2 + side3) / 2
        return sqrt(s * (s - side1) * (s - side2) * (s - side3))
    }

    override fun GetOutlineColor(): Int {
        try {
            return Integer.decode("#$outlineColor")
        } catch (e: IllegalArgumentException) {
            println("#$outlineColor is unacceptable outline color, I made it #ffffff")
            return 16777215
        }
    }

    override fun GetPerimeter(): Double {
        val side1 = CLineSegment(vertex1, vertex2).GetPerimeter()
        val side2 = CLineSegment(vertex2, vertex3).GetPerimeter()
        val side3 = CLineSegment(vertex3, vertex1).GetPerimeter()
        return side1 + side2 + side3
    }

    override fun ToString(): String {
        val builder = StringBuilder()
        builder.appendLine("Type: triangle")
        builder.appendLine("Vertex 1: (${GetVertex1().xCoord}, ${GetVertex1().yCoord})")
        builder.appendLine("Vertex 2: (${GetVertex2().xCoord}, ${GetVertex2().yCoord})")
        builder.appendLine("Vertex 3: (${GetVertex3().xCoord}, ${GetVertex3().yCoord})")
        builder.appendLine("Outline Color: ${GetOutlineColor()}")
        if (fillColor != null) {
            builder.appendLine("Fill Color: ${GetFillColor()}")
        }
        builder.appendLine("Area: ${"%.2f".format(GetArea())}")
        builder.appendLine("Perimeter: ${"%.2f".format(GetPerimeter())}")
        return builder.toString()
    }

    override fun GetFillColor(): Int {
        try {
            return Integer.decode("#$fillColor")
        } catch (e: IllegalArgumentException) {
            println("#$fillColor is unacceptable fill color, I made it #000000")
            return 0
        }
    }

    fun GetVertex1(): CPoint {
        return vertex1
    }

    fun GetVertex2(): CPoint {
        return vertex2
    }

    fun GetVertex3(): CPoint {
        return vertex3
    }
}