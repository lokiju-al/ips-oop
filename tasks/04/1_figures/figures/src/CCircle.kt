import kotlin.math.PI
import kotlin.math.pow

class CCircle (
    private val center: CPoint,
    private val radius: Double,
    private val outlineColor: String? = "fff",
    private val fillColor: String?,
) : ISolidShape {
    override fun GetArea(): Double {
        return (PI * radius).pow(2)
    }

    override fun GetOutlineColor(): Int {
        return Integer.decode("#$outlineColor")
    }

    override fun GetPerimeter(): Double {
        return 2 * PI * radius
    }

    override fun ToString(): String {
        val builder = StringBuilder()
        builder.appendLine("Type: circle")
        builder.appendLine("Center: (${GetCenter().x}, ${GetCenter().y})")
        builder.appendLine("Radius: ${GetRadius()}")
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

    fun GetCenter(): CPoint {
        return center
    }

    fun GetRadius(): Double {
        return radius
    }
}