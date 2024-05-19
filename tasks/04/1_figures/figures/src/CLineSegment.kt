import kotlin.math.pow
import kotlin.math.sqrt

class CLineSegment(
    private val startPoint: CPoint,
    private val endPoint: CPoint,
    private val outlineColor: String? = "ffffff",
) : IShape {
    override fun GetArea(): Double {
        return 0.0
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
        return sqrt((endPoint.xCoord - startPoint.xCoord).pow(2) + (endPoint.yCoord - startPoint.yCoord).pow(2))
    }

    override fun ToString(): String {
        val builder = StringBuilder()
        builder.appendLine("Type: line segment")
        builder.appendLine("Start point: (${GetStartPoint().xCoord}, ${GetStartPoint().yCoord})")
        builder.appendLine("End point: (${GetEndPoint().xCoord}, ${GetEndPoint().yCoord})")
        builder.appendLine("Outline Color: ${GetOutlineColor()}")
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