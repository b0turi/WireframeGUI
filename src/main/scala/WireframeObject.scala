import java.awt.{Graphics, Point}

abstract class WireframeObject {
  protected var xPosition = Window.WIDTH/2
  protected var yPosition = Window.HEIGHT/2

  protected var _width = 0
  protected var _height = 0

  private var locked = false
  protected var selected = false

  private var annotations:Set[Annotation] = Set()
  private var parent:Option[WireframeGroup] = Option.empty

  def move(xDiff:Int, yDiff:Int):Unit = {
    xPosition += xDiff
    yPosition += yDiff
  }

  def draw(g:Graphics):Unit

  def mouseInArea(mouse:Point):Boolean = (xPosition to (xPosition + _width) contains mouse.x) && (yPosition to (yPosition + _height) contains mouse.y)

  def isSelected:Boolean = selected

  def select():Unit = {
    selected = true
  }

  def deselect():Unit = {
    selected = false
  }
}
