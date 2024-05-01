import kotlin.math.sqrt

class CTriangle (
    private val vertex1: CPoint,
    private val vertex2: CPoint,
    private val vertex3: CPoint,
    private val outlineColor: String? = "fff",
    private val fillColor: String?,
) : ISolidShape {
    override fun GetArea(): Double {
        val side1 = CLineSegment(vertex1, vertex2).GetPerimeter()
        val side2 = CLineSegment(vertex2, vertex3).GetPerimeter()
        val side3 = CLineSegment(vertex3, vertex1).GetPerimeter()
        val s = (side1 + side2 + side3) / 2
        return sqrt(s * (s - side1) * (s - side2) * (s - side3))
    }

    override fun GetOutlineColor(): Int {
        return Integer.decode("#$outlineColor")
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
        builder.appendLine("Vertex 1: (${GetVertex1().x}, ${GetVertex1().y})")
        builder.appendLine("Vertex 2: (${GetVertex2().x}, ${GetVertex2().y})")
        builder.appendLine("Vertex 3: (${GetVertex3().x}, ${GetVertex3().y})")
        builder.appendLine("Stroke Color: ${GetOutlineColor()}")
        if (fillColor != null) {
            builder.appendLine("Fill Color: ${GetFillColor()}")
        }
        builder.appendLine("Area: ${"%.2f".format(GetArea())}")
        builder.appendLine("Perimeter: ${"%.2f".format(GetPerimeter())}")
        return builder.toString()
    }

    override fun GetFillColor(): Int {
        return Integer.decode("#$fillColor")
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