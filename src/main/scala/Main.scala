package hotloop

object Main extends App {

  def show( is: Array[Int] ) = println( is.mkString("[",", ","]") )

  val ary = Array( 0, 1, 2 )
  
  show(ary)
  
  HotLoop.fill( 3, ary, 100 )

  show( ary )


}
