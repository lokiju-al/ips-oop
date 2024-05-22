import javafx.application.Application
import javafx.scene.Group
import javafx.scene.Scene
import javafx.scene.paint.Color
import javafx.scene.shape.Circle
import javafx.scene.shape.Line
import javafx.scene.shape.Polygon
import javafx.scene.shape.Rectangle
import javafx.stage.Stage

class CCanvas : Application() {
    override fun start(stage: Stage) {
        val inputFigures = readFiguresFromFile("input.txt")
        val root = Group()
        for (figure in inputFigures) {
            when (figure) {
                is CLineSegment -> {
                    val line = Line(figure.GetStartPoint().xCoord, figure.GetStartPoint().yCoord, figure.GetEndPoint().xCoord, figure.GetEndPoint().yCoord)
                    line.stroke = Color.web(String.format("%06X", figure.GetOutlineColor()))
                    line.strokeWidth = 3.0
                    root.children.add(line)
                }
                is CTriangle -> {
                    val triangle = Polygon()
                    triangle.points.addAll(figure.GetVertex1().xCoord, figure.GetVertex1().yCoord, figure.GetVertex2().xCoord,
                        figure.GetVertex2().yCoord, figure.GetVertex3().xCoord, figure.GetVertex3().yCoord)
                    triangle.fill = Color.web(String.format("%06X", figure.GetFillColor()))
                    triangle.stroke = Color.web(String.format("%06X", figure.GetOutlineColor()))
                    triangle.strokeWidth = 3.0
                    root.children.add(triangle)
                }
                is CCircle -> {
                    val circle = Circle(figure.GetCenter().xCoord, figure.GetCenter().yCoord, figure.GetRadius())
                    circle.fill = Color.web(String.format("%06X", figure.GetFillColor()))
                    circle.stroke = Color.web(String.format("%06X", figure.GetOutlineColor()))
                    circle.strokeWidth = 3.0
                    root.children.add(circle)
                }
                is CRectangle -> {
                    val rectangle = Rectangle(figure.GetLeftTop().xCoord, figure.GetLeftTop().yCoord, figure.GetWidth(), figure.GetHeight())
                    rectangle.fill = Color.web(String.format("%06X", figure.GetFillColor()))
                    rectangle.stroke = Color.web(String.format("%06X", figure.GetOutlineColor()))
                    rectangle.strokeWidth = 3.0
                    root.children.add(rectangle)
                }
            }
        }

        val scene = Scene(root, 600.0, 700.0)
        stage.title = "Figures"
        stage.scene = scene
        stage.show()
    }
}