import java.awt.Graphics

import scala.util.Random

class Paragraph (width:Int, height:Int, align:TextObject.Alignment.Value) extends TextObject ( width, height, align) {
  var lineLength:Random = new Random(5)
  var numLines = 15

  textVisible = false
  override def draw(g: Graphics): Unit = {
    lineLength = new Random(5)
    for(a <- 0 to numLines) {
      val yStep = (padding + fontSize) * a
      if (yStep < _height) {
        var lineWidth = ((lineLength.nextDouble() / 4 + 0.75) * _width).toInt
        g.fillRect(xPosition + padding + alignDiff(lineWidth), yPosition + yStep , lineWidth, fontSize)
      }
    }
    super.draw(g)
  }

}
