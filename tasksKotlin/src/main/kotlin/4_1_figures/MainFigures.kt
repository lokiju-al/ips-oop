//import javafx.application.Application
import java.io.File


fun findFigureWithMaxArea(figures: List<IShape>): IShape? {
    return figures.maxByOrNull { it.GetArea() }
}

fun findFigureWithMinPerimeter(figures: List<IShape>): IShape? {
    return figures.minByOrNull { it.GetPerimeter() }
}

fun readFiguresFromFile(filePath: String): MutableList<IShape> {
    val inputFigures = mutableListOf<IShape>()
    // разделить на чтение строки и несколько функций для чтения фигур
    val inputFile = File(filePath)
    if (inputFile.exists()) {
        inputFile.forEachLine { line ->
            val tokens = line.split(" ")
            try {
                when (tokens[0]) {
                    "lineSegment" -> {
                        if (tokens.size >= 6) {
                            val startPoint = CPoint.MakeCPont(tokens[1], tokens[2])
                            val endPoint = CPoint.MakeCPont(tokens[3], tokens[4])
                            val outlineColor = tokens[5]
                            inputFigures.add(CLineSegment(startPoint, endPoint, outlineColor))
                        }
                    }

                    "triangle" -> {
                        if (tokens.size >= 8) {
                            val vertex1 = CPoint.MakeCPont(tokens[1], tokens[2])
                            val vertex2 = CPoint.MakeCPont(tokens[3], tokens[4])
                            val vertex3 = CPoint.MakeCPont(tokens[5], tokens[6])
                            val outlineColor = tokens[7]
                            val fillColor = if (tokens.size > 8) tokens[8] else null
                            inputFigures.add(CTriangle(vertex1, vertex2, vertex3, outlineColor, fillColor))
                        }
                    }

                    "rectangle" -> {
                        if (tokens.size >= 6) {
                            val leftUpCornerCoordinate = CPoint.MakeCPont(tokens[1], tokens[2])
                            val width = tokens[3].toDouble()
                            val height = tokens[4].toDouble()
                            val outlineColor = tokens[5]
                            val fillColor = if (tokens.size > 6) tokens[6] else null
                            inputFigures.add(CRectangle(leftUpCornerCoordinate, width, height, outlineColor, fillColor))
                        }
                    }

                    "circle" -> {
                        if (tokens.size >= 5) {
                            val center = CPoint.MakeCPont(tokens[1], tokens[2])
                            val radius = tokens[3].toDouble()
                            val outlineColor = tokens[4]
                            val fillColor = if (tokens.size > 5) tokens[5] else null
                            inputFigures.add(CCircle(center, radius, outlineColor, fillColor))
                        }
                    }
                    else -> println("Figure is unacceptable")
                }
            } catch (e: IllegalArgumentException){
                println(e.message)
            }
        }
    } else {
        println("Input file not found")
    }

    return inputFigures
}

fun main() {
    val inputFigures = readFiguresFromFile("input.txt")

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
//        Application.launch(CCanvas::class.java)
    } else {
        println("No figures found in the input file")
    }
}