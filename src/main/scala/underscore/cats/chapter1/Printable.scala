package underscore.cats.chapter1

trait Printable[A] {
  def format(value: A): String
}

object PrintableInstances {
  implicit val stringWriter: Printable[String] =
    (value: String) => value

  implicit val intWriter: Printable[Int] =
    (value: Int) => value.toString

  implicit val catWriter: Printable[Cat] =
    (cat: Cat) => s"${cat.name} is a ${cat.age} ${cat.color} cat."
}

object Printable {
  def format[A](value: A)(implicit printable: Printable[A]): String = printable.format(value)

  def print[A](value: A)(implicit printable: Printable[A]): Unit = println(printable.format(value))
}

object PrintableSyntax {
  implicit class PrintableOps[A](value: A) {
    def format(implicit p: Printable[A]): String = p.format(value)

    def print(implicit p: Printable[A]): Unit = println(p.format(value))
  }
}

final case class Cat(name: String, age: Int, color: String)

  object Bosh {
    def main(args: Array[String]): Unit = {
      import PrintableInstances._
//      import Printable._

      import PrintableSyntax._

      val cat = Cat("john", 12, "red")

      cat.print
    }
  }
