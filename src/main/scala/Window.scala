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

    val firstObj = new Image(50, 50)
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

  def updateFrame(mouseLocation:Point):Unit = {
    for(obj <- objects) {
      if(obj.mouseInArea(mouseLocation) && !obj.isSelected) {
        obj.select()
      }else if(obj.isSelected) {
        obj.deselect()
      }
    }
    paint(getGraphics)
  }
}