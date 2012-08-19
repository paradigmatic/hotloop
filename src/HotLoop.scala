package hotloop

import scala.reflect.makro.Context

object HotLoop {

  def updateAry( ary: Array[Int], i: Int, x: Int ): Unit = macro updateAry_impl

  def updateAry_impl( c: Context )
  ( ary: c.Expr[Array[Int]], i: c.Expr[Int], x: c.Expr[Int] ): c.Expr[Unit] = {

    import c.universe._

    val up = Apply( Select( ary.tree, "update"), List(i.tree, x.tree) )

    c.Expr[Unit](Block(up))
  }

  def fill(size:Int, ary: Array[Int], x: Int ) = macro fill_impl

  def fill_impl( c: Context )
  (size:c.Expr[Int], ary: c.Expr[Array[Int]], x: c.Expr[Int]): c.Expr[Unit] = {
    import c.universe._        
    def const(x:Int) = Literal(Constant(x))

    val Literal(Constant(arySize:Int)) = size.tree

    //Precompute x
    val valName = newTermName("$value")
    val valdef = ValDef( Modifiers(), valName, TypeTree(typeOf[Int]), x.tree )

    val updates = List.tabulate( arySize ){
      i => Apply( Select( ary.tree, "update"), List( const(i), Ident(valName) ) )
    }

    val insts = valdef :: updates

    c.Expr[Unit](Block(insts:_*))
  }
/*
  def foreach( size: Int, ary: Array[Int], f: Int=>Unit ): Unit = 
    macro foreach_impl

  def foreach_impl( c: Context )
  (size:c.Expr[Int], ary: c.Expr[Array[Int]], f: c.Expr[Int=>Unit]): c.Expr[Unit] = {
    import c.universe._        
    def const(x:Int) = Literal(Constant(x))

    val Literal(Constant(arySize:Int)) = size.tree

    //Precompute x
    val $arr = ary.splice
    val $f = f.splice

    val updates = List.tabulate( arySize ){
      i => reify{ f($arr(i)) }
    }

    val insts = valdef :: updates

    c.Expr[Unit](Block(insts:_*))
  }



*/


/*
  def foreach( ary: Array[Int], f: (Int)=>Unit ) = macro foreach_impl

  def foreach_impl( c: Context )
  ( ary: c.Expr[Array[Int]], f: c.Expr[Int=>Unit]): c.Expr[Unit] = {

    import c.universe._    

    c.Expr[Unit](Block())
  }

*/

}
