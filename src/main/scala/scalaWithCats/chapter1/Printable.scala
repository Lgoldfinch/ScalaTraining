package scalaWithCats.chapter1


trait Printable[A] {
  def format(value: A): String
}

final case class Cat(name: String, age: Int, colour: String)

object PrintableInstances {
  implicit val stringPrinter: Printable[String] = new Printable[String] {
    def format(input: String): String = input
  }

  implicit val integerPrinter: Printable[Int] = new Printable[Int] {
    def format(input: Int): String = input.toString
  }
}
  object Cat {
    implicit val catPrinter: Printable[Cat] = new Printable[Cat] {
      def format(cat: Cat): String = s"${cat.name} is a ${cat.age} year-old ${cat.colour} cat."
    }
  }

object Printable {
  def format[A](input: A)(implicit printable: Printable[A]): String = printable.format(input)
  def print[A](input: A)(implicit printable: Printable[A]): Unit = println(format(input))
}

object PrintableSyntax {
  implicit class PrintableOps[A](value: A) {
    def format(implicit p: Printable[A]): String = p.format(value)

    def print(implicit p: Printable[A]): Unit = println(p.format(value))
  }
}

object Main extends App {
  import PrintableSyntax._
  val cat = Cat("yo", 18, "black")
    cat.print


}

