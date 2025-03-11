import kotlin.math.PI
import kotlin.math.pow

class CCircle (
    private val center: CPoint,
    private val radius: Double,
    private val outlineColor: String? = "ffffff",
    private val fillColor: String? = null,
) : ISolidShape {
    override fun GetArea(): Double {
        return PI * radius.pow(2)
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
        return 2 * PI * radius
    }

    override fun ToString(): String {
        val builder = StringBuilder()
        builder.appendLine("Type: circle")
        builder.appendLine("Center: (${"%.2f".format(GetCenter().xCoord)}, ${"%.2f".format(GetCenter().yCoord)})")
        builder.appendLine("Radius: ${"%.2f".format(GetRadius())}")
        builder.appendLine("Outline Color: ${GetOutlineColor()}")
        if (fillColor != null) {
            builder.appendLine("Fill Color: ${GetFillColor()}")
        }
        builder.appendLine("Area: ${"%.2f".format(GetArea())}")
        builder.appendLine("Perimeter: ${"%.2f".format(GetPerimeter())}")
        return builder.toString()
    }
 // выдельить в абстрактные классы некоторые методы
    override fun GetFillColor(): Int {
        try {
            return Integer.decode("#$fillColor")
        } catch (e: IllegalArgumentException) {
            println("#$fillColor is unacceptable fill color, I made it #000000")
            return 0
        }
    }

    fun GetCenter(): CPoint {
        return center
    }

    fun GetRadius(): Double {
        return radius
    }
}