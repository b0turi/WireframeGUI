class BarObject (width:Int, percentage:Double) extends ScalableObject{
  val defaultHeight = 24

  _width = width
  _height = defaultHeight
  var _percentage = percentage

  yScalable = false
}
