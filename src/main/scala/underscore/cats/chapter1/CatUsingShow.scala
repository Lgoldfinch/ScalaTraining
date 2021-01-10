package underscore.cats.chapter1

import cats.Show
import cats.instances.{int, string}
import cats.syntax.show._

object CatUsingShow {
  def main(args: Array[String]): Unit = {

    /**
   * defining custom instances
   */

  implicit val catShow: Show[Cat] =
    Show.show(cat => cat.name + cat.color + cat.age)

      val cat = Cat("john", 12, "red")

   println( cat.show)





  }
}