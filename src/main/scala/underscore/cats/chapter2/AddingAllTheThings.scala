package underscore.cats.chapter2
import cats.instances.int._
import cats.instances.option._
import cats.Monoid
import cats.syntax.OrderSyntax
import cats.syntax.semigroup._
import underscore.cats.chapter1.{Json, JsonWriter}

object AddingAllTheThings {
  def main(args: Array[String]): Unit = {

    val intMonoid = Monoid[Int]

    def add(items: List[Int]): Int = items.foldLeft(intMonoid.empty)(_ |+| _)

    println(add(List(1, 2, 3, 4)))

    def addGeneric[A: Monoid](items: List[A]): A = items.foldLeft(Monoid[A].empty)(_ |+| _)

    val optionList = List(Some(1), Some(4), None)

    println(addGeneric(optionList))

    case class Order(totalCost: Double, quantity: Double) {
      val total: Double = totalCost * quantity
    }
    val orderMonoid = Monoid[Order]

    implicit val order: Monoid[Order] =
      new Monoid[Order] {
        override def combine(x: Order, y: Order): Order = {
          val totalCost = x.totalCost + y.totalCost
          val quantity = x.quantity + y.quantity
            Order(totalCost, quantity)
        }

        override def empty: Order = Order(0, 0)
      }


  }


}
