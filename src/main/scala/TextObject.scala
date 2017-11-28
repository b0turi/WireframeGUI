import java.awt.{Font, Graphics, Point}
import util.control.Breaks._
object TextObject {
  object Alignment extends Enumeration {
    val LEFT, RIGHT, CENTER, JUSTIFY = Value
  }
}

class TextObject(width:Int, height:Int, align:TextObject.Alignment.Value) extends ScalableObject {
  private val text = "Lorem ipsum dolor sit amet et delectus accommodare his consul coplosae legendos at vix ad putent delectus delicata usu. Vidit dissentiet eos cu euem an"
  private var alignment = align
  private val fontSize = 16

  private val padding = 3


  _width = width
  _height = height

  def setAlignment(align:TextObject.Alignment.Value):Unit = { alignment = align }

  override def draw(g: Graphics): Unit = {


    g.setFont(new Font("Serif", 0, fontSize))
    var lines:List[String] = getLines(g)
    for(a <- lines.indices) {
      var lineWidth = g.getFontMetrics.stringWidth(lines(a))
      val lineYPosition = yPosition + (1 + a) * (fontSize + padding)
      if(lineYPosition < yPosition + _height) {
        if(alignment == TextObject.Alignment.JUSTIFY) {
          lines = lines.patch(a, Seq(justify(lineWidth, lines(a), g)), 1)
        }
        g.drawString(lines(a), xPosition + alignDiff(lineWidth), lineYPosition)
      }
    }

    super.draw(g)
  }

  def justify(initialWidth:Int, initialLine:String, g:Graphics):String = {
    var lineWidth = initialWidth
    var line = initialLine
    breakable {
      while (lineWidth < _width * 0.9 && lineWidth > _width * 0.75) {
        val augmentedLine = new StringBuilder()
        val words = line.split(" ")
        if (words.length == 1) {
          break
        }
        for (word <- words) {
          augmentedLine.append(word + "  ")
        }
        line = augmentedLine.toString
        lineWidth = g.getFontMetrics.stringWidth(line)
      }
    }
    line
  }

  def alignDiff(lineWidth:Int):Int = {
    if (alignment == TextObject.Alignment.RIGHT) {
      _width - lineWidth
    } else if (alignment == TextObject.Alignment.CENTER) {
      (_width - lineWidth)/2
    } else { //LEFT or JUSTIFY
      0
    }
  }

  def getLines(g:Graphics):List[String] = {
    var xStep = 0
    var yStep = 0
    val words = text.split(" ")
    var lines:List[String] = List()
    var textLine = new StringBuilder()
    for(a <- words.indices) {
      xStep += g.getFontMetrics.stringWidth(words(a)+ " ")
      textLine.append(words(a)+" ")
      if(a < words.length - 1 && xStep >= _width - g.getFontMetrics.stringWidth(words(a+1))) {
        lines = textLine.toString :: lines
        textLine = new StringBuilder()
        xStep = 0
      }
    }
    lines = textLine.toString :: lines

    lines.reverse
  }
}
