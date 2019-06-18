import scala.math.Ordering

implicit val absOrdering = Ordering.fromLessThan[Int]((x,y) => Math.abs(x)  < Math.abs(y))

List(-1 , 2, 4, 3).sorted

println(assert(List(-4, -1, 0, 2, 3).sorted == List(0, -1, 2, 3, -4)))



trait Equal[A] {
  def equal(a: A, b: A): Boolean
}

object Equal {
  def apply[A](implicit instance: Equal[A]) = instance

  implicit class ReallyEqual[A](one: A) {
    def ===(theOther: A)(implicit equal: Equal[A]): Boolean = equal.equal(one, theOther)
  }
}

case class Person(name: String, email: String)

 object NameComparison extends Equal[String] {
   def equal(a: String, b: String): Boolean = a == b
}

object NameAndEmailImplicit {
  implicit object PersonComparison extends Equal[Person] {
    override def equal(a: Person, b: Person) = a.email == b. email && a.name == b.name
  }
}

object PersonComparison extends Equal[Person] {

  def equal(a: Person, b: Person): Boolean = a.email == b. email && a.name == b.name
}

object Eq {
  def apply[A](a: A, b: A)(implicit equal: Equal[A]): Boolean =
    equal.equal(a,b)
}



implicit class ExtraStringMethods(str: String) {
  val vowels = Seq('a','e','i','o','u')

  def numberOfVowels =
    str.toList.count(x => vowels.contains(x))
}

"the quick brown fox".numberOfVowels

implicit object IntImplicits {

  implicit class IntOperations(noOYeahs: Int) {

    def times(fn: Int => Unit): Unit =
      for(i <- 0 until noOYeahs) fn(i)

    def yeah(): Unit =
      times(_ => println("oh yeah!" ))
  }
}
import IntImplicits.IntOperations
import Equal._

implicit val a = new Equal[String] {
  def equal(s1: String, s2: String) = s1.toLowerCase() == s2.toLowerCase()
}

implicit val b = new Equal[Person] {
  def equal(a: Person, b: Person) = a.email == b. email && a.name == b.name
}

case class Example(name: String)
implicit val implicitExample = Example("implicit")

implicitly(Example)

val p1 = Person("bing", "bong")
val p2 = Person("bing", "bong")
"abcd".===("ABCD")
p1.===(p2)
2.yeah()
3.yeah()
3.times(i => println(s"Look - it's the number $i!"))

