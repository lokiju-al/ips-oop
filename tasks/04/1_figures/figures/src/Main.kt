import java.io.File

fun findFigureWithMaxArea(figures: List<IShape>): IShape? {
    return figures.maxByOrNull { it.GetArea() }
}

fun findFigureWithMinPerimeter(figures: List<IShape>): IShape? {
    return figures.minByOrNull { it.GetPerimeter() }
}

fun main() {
    val inputFigures = mutableListOf<IShape>()

    val inputFile = File("input.txt")
    if (inputFile.exists()) {
        inputFile.forEachLine { line ->
            val tokens = line.split(" ")
            when (tokens[0]) {
                "lineSegment" -> {
                    if (tokens.size >= 4) {
                        val startPoint = CPoint(tokens[1].toDouble(), tokens[2].toDouble())
                        val endPoint = CPoint(tokens[3].toDouble(), tokens[4].toDouble())
                        val outlineColor = tokens[5]
                        inputFigures.add(CLineSegment(startPoint, endPoint, outlineColor))
                    }
                }

                "triangle" -> {
                    if (tokens.size >= 8) {
                        val vertex1 = CPoint(tokens[1].toDouble(), tokens[2].toDouble())
                        val vertex2 = CPoint(tokens[3].toDouble(), tokens[4].toDouble())
                        val vertex3 = CPoint(tokens[5].toDouble(), tokens[6].toDouble())
                        val outlineColor = tokens[7]
                        val fillColor = if (tokens.size > 8) tokens[8] else null
                        inputFigures.add(CTriangle(vertex1, vertex2, vertex3, outlineColor, fillColor))
                    }
                }

                "rectangle" -> {
                    if (tokens.size >= 6) {
                        val leftUpCornerCoordinate = CPoint(tokens[1].toDouble(), tokens[2].toDouble())
                        val width = tokens[3].toDouble()
                        val height = tokens[4].toDouble()
                        val outlineColor = tokens[5]
                        val fillColor = if (tokens.size > 6) tokens[6] else null
                        inputFigures.add(CRectangle(leftUpCornerCoordinate, width, height, outlineColor, fillColor))
                    }
                }

                "circle" -> {
                    if (tokens.size >= 5) {
                        val center = CPoint(tokens[1].toDouble(), tokens[2].toDouble())
                        val radius = tokens[3].toDouble()
                        val outlineColor = tokens[4]
                        val fillColor = if (tokens.size > 5) tokens[5] else null
                        inputFigures.add(CCircle(center, radius, outlineColor, fillColor))
                    }
                }
                else -> println("Figure is unacceptable")
            }
        }
    } else {
        println("Input file not found")
        return
    }

    if (inputFigures.isNotEmpty()) {
        val figureWithMaxArea = findFigureWithMaxArea(inputFigures)
        val figureWithMinPerimeter = findFigureWithMinPerimeter(inputFigures)

        if (figureWithMaxArea != null) {
            println("Figure with max area:")
            println(figureWithMaxArea.ToString())
        } else {
            println("No figures found")
        }

        if (figureWithMinPerimeter != null) {
            println("Figure with min perimeter:")
            println(figureWithMinPerimeter.ToString())
        } else {
            println("No figures found")
        }
    } else {
        println("No figures found in the input file")
    }
}