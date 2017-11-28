import java.awt._
import java.awt.event.{MouseListener, WindowAdapter, WindowEvent}


object Window extends Frame
{
  final val WIDTH = 400
  final val HEIGHT = 400

  var objects:scala.List[WireframeObject] = scala.List()


  def main(args:Array[String]):Unit = {
    val mouseInput = new WireframeMouseListener
    setSize(WIDTH, HEIGHT)
    setTitle("Wireframe GUI")
    addWindowListener(new WindowAdapter() { override def windowClosing(e: WindowEvent): Unit = System.exit(0) })
    addMouseListener(mouseInput)
    addMouseMotionListener(mouseInput)

    val firstObj = new Slider(100, 1)
    addObject(firstObj)

    this.setVisible(true)
  }

  override def paint(g:Graphics):Unit = {
    g.clearRect(0,0,WIDTH,HEIGHT)
    for(obj <- objects) {
      obj.draw(g)
    }
  }

  def addObject(obj:WireframeObject):Unit = {
    objects = obj :: objects
  }

  def establishPoints(mouseLocation:Point):Unit = {
    for(obj <- objects) {
      if(obj.isSelected) {
        obj.establishSelectionPoint(mouseLocation)
      }
    }
  }

  def updateFrame(mouseLocation:Point):Unit = {
    for(obj <- objects) {
      if(!obj.isSelected && obj.mouseInArea(mouseLocation)) {
        obj.select()
      }else if(obj.isSelected && !obj.mouseInArea(mouseLocation)) {
        obj.deselect()
      }
    }
    paint(getGraphics)
  }

  def moveSelected(mouseLocation:Point, lastMouseLocation:Point):Unit = {
    val xDifference = mouseLocation.x - lastMouseLocation.x
    val yDifference = mouseLocation.y - lastMouseLocation.y

    for(obj <-  objects) {
      if(obj.isSelected && obj.selectionPoint == 0) {
        obj.move(xDifference, yDifference)
      } else if(obj.isSelected && obj.selectionPoint != 0) {
        obj.scale(xDifference, yDifference)
      }
    }
    paint(getGraphics)
  }
}