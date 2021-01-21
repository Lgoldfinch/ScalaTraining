package underscore.cats.chapter2

object MonoidInCats {
  import cats.instances.int._ // for Monoid
  import cats.instances.option._ // for Monoid
  import cats.Monoid
  import cats.instances.string._ // for Monoid

  /**
   * Companion object has an apply method that returns the type class instance for a type. So if we want the monoid instance for String
   */

  Monoid[String].combine("hello", "there")

  Monoid[String].empty

  val a = Option(22)
  val b = Option(33)

  Monoid[Option[Int]].combine(a, b)

  /**
   * If you dont need the empty you can just use Semigroup.
   */

  /**
   * Monoid syntax
   */

  import cats.syntax.semigroup._ // for |+|

  val nosh = "lol" |+| "asdd" |+| Monoid[String].empty

  
}
