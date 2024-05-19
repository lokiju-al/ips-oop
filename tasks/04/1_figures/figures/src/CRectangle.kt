class CRectangle (
    private val leftTopCoordinate: CPoint,
    private val width: Double,
    private val height: Double,
    private val outlineColor: String? = "ffffff",
    private val fillColor: String?,
) : ISolidShape {
    override fun GetArea(): Double {
        return width * height
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
        return (width + height) * 2
    }

    override fun ToString(): String {
        val builder = StringBuilder()
        builder.appendLine("Type: rectangle")
        builder.appendLine("Left top: (${GetLeftTop().xCoord}, ${GetLeftTop().yCoord})")
        builder.appendLine("Right bottom: (${GetRightBottom().xCoord}, ${GetRightBottom().yCoord})")
        builder.appendLine("Width: ${GetWidth()}")
        builder.appendLine("Height: ${GetHeight()}")
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

    fun GetLeftTop(): CPoint {
        return leftTopCoordinate
    }

    fun GetRightBottom(): CPoint {
        val rightBottomCornerCoordinate = CPoint(leftTopCoordinate.xCoord + width, leftTopCoordinate.yCoord + height)
        return rightBottomCornerCoordinate
    }

    fun GetWidth(): Double {
        return width
    }

    fun GetHeight(): Double {
        return height
    }
}