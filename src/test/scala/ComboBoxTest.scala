import org.scalatest.FlatSpec

class ComboBoxTest extends FlatSpec {
  val comboBox = classOf[ComboBox]
  val obj = new ComboBox(List("asdf", "qwer", "zxcv"))
  "A ComboBox" should "have a width and height of the given default values" in {
    val width = comboBox.getSuperclass.getDeclaredField("_width")
    width.setAccessible(true)

    val objWidth = width.get(obj).asInstanceOf[Int]

    val height = comboBox.getSuperclass.getDeclaredField("_height")
    height.setAccessible(true)

    val objHeight = height.get(obj).asInstanceOf[Int]

    assert(objWidth == obj.COMBO_BOX_WIDTH)
    assert(objHeight == obj.COMBO_BOX_HEIGHT)
  }

  it should "have an open height value equivalent to the default height plus the number of options times the default height" in {
    //The given object has 3 values
    assert(obj.openHeight == 3 * obj.COMBO_BOX_HEIGHT)
  }
}
