import java.awt.Graphics

import scala.util.Random

class ListObject(width:Int, height:Int) extends TextObject(width, height, TextObject.Alignment.LEFT) {
  var lineLength:Random = new Random()
  var numLines = 15

  contentHeight = numLines * (fontSize + padding)
  textVisible = false
  override def draw(g: Graphics): Unit = {
    val indentedWidth = _width - fontSize
    lineLength = new Random(3)
    for(a <- 0 to offset) {
      lineLength.nextDouble()
    }
    for(a <- offset to numLines) {
      val yStep = (padding + fontSize) * (a - offset)
      if (yStep < _height) {
        var lineWidth = ((lineLength.nextDouble() / 4 + 0.75) * indentedWidth).toInt
        g.fillRect(xPosition + padding, yPosition + yStep + fontSize / 4, fontSize/2, fontSize / 2)
        g.fillRect(fontSize + xPosition + padding + alignDiff(lineWidth), yPosition + yStep , lineWidth, fontSize)
      }
    }
    super.draw(g)
  }
}
