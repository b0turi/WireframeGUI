import java.awt.{Color, Graphics, Point}
abstract class ScalableObject extends WireframeObject{
  protected var xScalable = true
  protected var yScalable = true

  private var scaling = false

  override def interact(xAmount:Int, yAmount:Int):Unit = {
    if(xScalable && mouseTarget <= 4) {
      _width += xAmount * scaleDiff.x
      xPosition += xAmount * ((1 + mouseTarget) % 2)
    }
    if(yScalable) {
      _height += yAmount * scaleDiff.y
      yPosition += yAmount * (1 - ((mouseTarget - 1) / 2))
    }
  }

  def scaleDiff:Point = {
    var outPoint = new Point
    if(mouseTarget % 2 == 0) {
      outPoint.x = -1
    } else {
      outPoint.x = 1
    }
    if(mouseTarget < 3) {
      outPoint.y = -1
    } else {
      outPoint.y = 1
    }

    outPoint
  }

  override def calculateMouseTarget(mouse: Point): Unit = {
    super.calculateMouseTarget(mouse)

    for(a <- 1 to 4) {
      if(pointInRange(xPosition + _width * (a % 2) - controlRadius,
                      xPosition + _width * (a % 2) + controlRadius,
                      yPosition + _height * ((a - 1) / 2) - controlRadius,
                      yPosition + _height * ((a - 1) / 2) + controlRadius, mouse)) {
        mouseTarget = a
      }
    }
  }

  override def draw(g: Graphics): Unit = {
    if(selected) {
      //Draw the controls to scale the object
      g.setColor(Color.GRAY)
      for (a <- 1 to 4) {
        g.fillOval(xPosition - controlRadius + _width * (a % 2), yPosition - controlRadius + _height * ((a - 1) / 2), controlRadius * 2, controlRadius * 2)
      }
    }
    super.draw(g)
  }
}
