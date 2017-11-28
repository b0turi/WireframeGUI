import org.scalatest.FlatSpec

class BarObjectTest extends FlatSpec{

  val barObject = classOf[BarObject]

  "A BarObject" should "have a height set to its default height" in {
    val obj = new BarObject(200, 0.5)
    val height = barObject.getSuperclass.getSuperclass.getDeclaredField("_height")
    height.setAccessible(true)
    val barHeight = height.get(obj).asInstanceOf[Int]

    assert(barHeight == obj.defaultHeight)
  }

  it should "be assigned the width it is passed in" in {
    val passedWidth = 200

    val obj = new BarObject(passedWidth, 0.5)
    val width = barObject.getSuperclass.getSuperclass.getDeclaredField("_width")
    width.setAccessible(true)
    val barWidth = width.get(obj).asInstanceOf[Int]

    assert(barWidth == passedWidth)
  }

  it should "not be scalable in the y direction, but scalable in the x direction" in {
    val obj = new BarObject(200, 0.5)

    val yScalable = barObject.getSuperclass.getDeclaredField("yScalable")
    yScalable.setAccessible(true)

    val xScalable = barObject.getSuperclass.getDeclaredField("xScalable")
    xScalable.setAccessible(true)

    val barYAccessible:Boolean = yScalable.get(obj).asInstanceOf[Boolean]
    val barXAccessible:Boolean = xScalable.get(obj).asInstanceOf[Boolean]
    assert(!barYAccessible)
    assert(barXAccessible)
  }

}
