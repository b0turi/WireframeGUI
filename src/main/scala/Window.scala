import java.awt._
import java.awt.event.{MouseListener, WindowAdapter, WindowEvent}


object Window extends Frame
{
  final val WIDTH = 600
  final val HEIGHT = 600

  var objects:scala.List[WireframeObject] = scala.List()


  def main(args:Array[String]):Unit = {
    val mouseInput = new WireframeMouseListener
    setSize(WIDTH, HEIGHT)
    setTitle("Wireframe GUI")
    addWindowListener(new WindowAdapter() { override def windowClosing(e: WindowEvent): Unit = System.exit(0) })
    addMouseListener(mouseInput)
    addMouseMotionListener(mouseInput)

    //Add all objects to the screen here

    val text = new TextObject(200, 100, TextObject.Alignment.CENTER)
    text.move(-280, -40)
    addObject(text)

    val progress = new ProgressBar(200, 0.25)
    val furtherProgress = new ProgressBar(200, 0.75)

    progress.move(-280, 80)
    furtherProgress.move(-280, 110)

    addObject(progress)
    addObject(furtherProgress)

    val paragraph = new Paragraph(200, 100, TextObject.Alignment.RIGHT)
    paragraph.move(-280, 140)
    addObject(paragraph)

    val headline = new Headline("This is sample", "This is even more sample")
    headline.move(-60, -260)
    addObject(headline)

    val slider = new Slider(200, 0.35)
    slider.move(-60, -190)
    addObject(slider)

    val rounded = new RoundedBox(200, 200, 20)
    rounded.move(-60, -140)
    rounded.lock()
    addObject(rounded)

    val list = new ListObject(170, 180)
    list.move(-60, -130)
    addObject(list)

    val scroll = new ScrollBar(180, list)
    scroll.move(110, -130)
    addObject(scroll)

    val image = new Image(200, 200)
    image.move(-280, -260)
    addObject(image)



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

  def setTargets(mouseLocation:Point):Unit = {
    for(obj <- objects) {
      if(obj.isSelected) {
        obj.calculateMouseTarget(mouseLocation)
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
      if(obj.isSelected && obj.mouseTarget == 0) {
        obj.move(xDifference, yDifference)
      } else if(obj.isSelected && obj.mouseTarget != 0) {
        obj.interact(xDifference, yDifference)
      }
    }
    paint(getGraphics)
  }

  def groupSelect(topLeft:Point, bottomRight:Point):Unit = {
    for(obj <- objects) {
      if((topLeft.x to bottomRight.x contains obj.center.x) && (topLeft.y to bottomRight.y contains obj.center.y)) {
        obj.select()
      }
    }
    paint(getGraphics)
  }
}