import kotlin.math.pow
import kotlin.math.sqrt

class CLineSegment(
    private val startPoint: CPoint,
    private val endPoint: CPoint,
    private val outlineColor: String? = "fff",
) : IShape {
    override fun GetArea(): Double {
        return 0.0
    }

    override fun GetOutlineColor(): Int {
        return Integer.decode("#$outlineColor")
    }

    override fun GetPerimeter(): Double {
        return sqrt((endPoint.x - startPoint.x).pow(2) + (endPoint.y - startPoint.y).pow(2))
    }

    override fun ToString(): String {
        val builder = StringBuilder()
        builder.appendLine("Type: line segment")
        builder.appendLine("Start point: (${GetStartPoint().x}, ${GetStartPoint().y})")
        builder.appendLine("End point: (${GetEndPoint().x}, ${GetEndPoint().y})")
        builder.appendLine("Stroke Color: ${GetOutlineColor()}")
        builder.appendLine("Area: ${"%.2f".format(GetArea())}")
        builder.appendLine("Perimeter: ${"%.2f".format(GetPerimeter())}")
        return builder.toString()
    }

    fun GetStartPoint(): CPoint {
        return startPoint
    }

    fun GetEndPoint(): CPoint {
        return endPoint
    }
}