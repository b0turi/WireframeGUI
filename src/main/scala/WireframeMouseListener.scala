import java.awt.event.{MouseEvent, MouseListener, MouseMotionListener}

class WireframeMouseListener extends MouseListener with MouseMotionListener {

  override def mouseExited(e: MouseEvent): Unit = {}

  override def mousePressed(e: MouseEvent): Unit = {}

  override def mouseReleased(e: MouseEvent): Unit = {}

  override def mouseEntered(e: MouseEvent): Unit = {}

  override def mouseClicked(e: MouseEvent): Unit = {
    Window.updateFrame(e.getPoint)
  }

  override def mouseDragged(e: MouseEvent): Unit = {

  }

  override def mouseMoved(e: MouseEvent): Unit = {}
}
