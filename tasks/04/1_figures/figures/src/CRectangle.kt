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
        return Integer.decode("#$outlineColor")
    }

    override fun GetPerimeter(): Double {
        return (width + height) * 2
    }

    override fun ToString(): String {
        val builder = StringBuilder()
        builder.appendLine("Type: rectangle")
        builder.appendLine("Left top: (${GetLeftTop().x}, ${GetLeftTop().y})")
        builder.appendLine("Right bottom: (${GetRightBottom().x}, ${GetRightBottom().y})")
        builder.appendLine("Width: ${GetWidth()}")
        builder.appendLine("Height: ${GetHeight()}")
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

    fun GetLeftTop(): CPoint {
        return leftTopCoordinate
    }

    fun GetRightBottom(): CPoint {
        val rightBottomCornerCoordinate = CPoint(leftTopCoordinate.x + width, leftTopCoordinate.y + height)
        return rightBottomCornerCoordinate
    }

    fun GetWidth(): Double {
        return width
    }

    fun GetHeight(): Double {
        return height
    }
}