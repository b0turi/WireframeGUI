import java.awt.Graphics
class ProgressBar(width:Int, percentage:Double) extends BarObject(width, percentage){
  override def draw(g: Graphics): Unit = {
    g.drawRect(xPosition, yPosition, _width, _height)
    g.fillRect(xPosition, yPosition, (_width * _percentage).toInt, _height)
    super.draw(g)
  }
}
