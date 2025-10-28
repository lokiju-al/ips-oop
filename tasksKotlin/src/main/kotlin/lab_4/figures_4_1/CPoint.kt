package lab_4.figures_4_1

class CPoint(
    private val x: Double,
    private val y: Double,
) {
    var xCoord: Double = x
    var yCoord: Double = y

    companion object {
        fun MakeCPont(xCoordinate: String, yCoordinate: String): CPoint {
            val coordinates = CPoint(0.0, 0.0)
            if (xCoordinate.toDoubleOrNull() != null) {
                coordinates.xCoord = xCoordinate.toDouble()
            } else {
                throw IllegalArgumentException("Coordinate must be a number")
            }
            if (yCoordinate.toDoubleOrNull() != null) {
                coordinates.yCoord = yCoordinate.toDouble()
            } else {
                throw IllegalArgumentException("Coordinate must be a number")
            }
            return coordinates
        }
    }
}