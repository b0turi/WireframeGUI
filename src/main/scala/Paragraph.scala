import java.awt.Graphics

import scala.util.Random

class Paragraph (width:Int, height:Int, align:TextObject.Alignment.Value) extends TextObject ( width, height, align) {
  var lineLength:Random = new Random()
  var numLines = 15

  contentHeight = numLines * (fontSize + padding)
  textVisible = false
  override def draw(g: Graphics): Unit = {
    lineLength = new Random(5)
    for(n <- 0 to offset) {
      lineLength.nextDouble()
    }
    for(a <- offset to numLines) {
      val yStep = (padding + fontSize) * (a - offset)
      if (yStep < _height) {
        var lineWidth = ((lineLength.nextDouble() / 4 + 0.75) * _width).toInt
        g.fillRect(xPosition + padding + alignDiff(lineWidth), yPosition + yStep , lineWidth, fontSize)
      }
    }
    super.draw(g)
  }

}
