package scalaWithCats.chapter2MonoidsSemigroups
//
//import cats.kernel.Monoid
//import cats.instances.all._
//import cats.syntax.semigroup._
//
//case class Order(totalCost: Double, quantity: Double)
//
//object AddingAllTheThings {
//
//  def add(items: List[Int]): Int = items.foldLeft(Monoid[Int].empty)(_ |+| _)
//
//  def addPt2[A: Monoid](items: List[A]): A =
//    items.foldLeft(Monoid[A].empty)(_ |+| _)
//
//  implicit val orderMonoid: Monoid[Order] = new Monoid[Order] {
//    def combine(a: Order, b: Order): Order = Order((a.quantity  + b.quantity), (a.totalCost * b.totalCost))
//    def empty: Order = Order(0.0,0.0)
//  }
//}
//
//
//
//object main extends App {
//  import AddingAllTheThings._
//  println(addPt2(List(Some(1), None, Some(2), None, Some(3))))

//}

