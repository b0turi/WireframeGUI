import java.awt.{Color, Graphics}
class ScalableObject extends WireframeObject{
  private var xScalable = true
  private var yScalable = true

  private val controlRadius = 5
  def scale(xAmount:Int, yAmount:Int):Unit = {
    _width += xAmount
    xPosition += xAmount/2

    _height += yAmount
    yPosition += yAmount/2
  }

  override def draw(g: Graphics): Unit = {
    if(selected) {
      //Draw the controls to scale the object
      g.setColor(Color.GRAY)
      for (a <- 1 to 4) {
        g.fillOval(xPosition - controlRadius + _width * (a % 2), yPosition - controlRadius + _height * ((a - 1) / 2), controlRadius * 2, controlRadius * 2)
      }
    }
  }
}
