import java.awt.{Font, Graphics}

class Headline (title:String, subtitle:String) extends TextObject(200, 100, TextObject.Alignment.LEFT){
  val titleFontSize = fontSize * 2
  textVisible = false

  private var xStep = 0
  private var yStep = 0
  override def draw(g: Graphics): Unit = {

    //Draw title
    drawLine(g, title, titleFontSize)
    drawLine(g, subtitle, fontSize)
    super.draw(g)

    xStep = 0
    yStep = 0
  }



  def drawLine(g:Graphics, text:String, size:Int):Unit = {
    g.setFont(new Font("Serif", 0, size))
    var words = text.split(" ")

    for(a <- words.indices) {
      if(yPosition + yStep < yPosition + _height)
        g.fillRect(xPosition + padding + xStep, yPosition + yStep, g.getFontMetrics.stringWidth(words(a)), size)
      xStep += g.getFontMetrics.stringWidth(words(a) + " ")
      if(a < words.length-1 && _width - xStep < g.getFontMetrics.stringWidth(words(a+1))) {
        newLine(size)
      }
    }
    newLine(size)
  }

  def newLine(size:Int):Unit = {
    xStep = 0
    yStep += size + padding
  }
}
