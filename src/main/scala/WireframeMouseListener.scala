import java.awt.event.{MouseEvent, MouseListener, MouseMotionListener}
import java.awt.Point

class WireframeMouseListener extends MouseListener with MouseMotionListener {
  var lastMousePosition:Point = new Point()
  var startDrag:Point = new Point()
  var endDrag:Point = new Point()
  override def mouseExited(e: MouseEvent): Unit = {}

  override def mousePressed(e: MouseEvent): Unit = {
    Window.setTargets(e.getPoint)
    startDrag = e.getPoint
  }

  override def mouseReleased(e: MouseEvent): Unit = {
    endDrag = e.getPoint
    Window.groupSelect(startDrag, endDrag)
  }

  override def mouseEntered(e: MouseEvent): Unit = {}

  override def mouseClicked(e: MouseEvent): Unit = {
    Window.updateFrame(e.getPoint)
  }

  override def mouseDragged(e: MouseEvent): Unit = {
    Window.moveSelected(e.getPoint, lastMousePosition)
    lastMousePosition = e.getPoint
  }

  override def mouseMoved(e: MouseEvent): Unit = {
    lastMousePosition = e.getPoint
  }
}
