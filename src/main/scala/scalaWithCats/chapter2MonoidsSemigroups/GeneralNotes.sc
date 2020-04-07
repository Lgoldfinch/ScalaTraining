import cats.Monoid
import cats.instances.string._
import cats.instances.int._
import cats.instances.option._
import cats.syntax.semigroup._

Monoid[String].combine("hi ", "there")

Monoid[String].empty

Monoid[Int].combine(1,2)
Monoid[Int].empty

val a = Option(22)

val b = Option(9)

Monoid[Option[Int]].combine(a,b)

val stringResult = "Hi " |+| "there" |+| Monoid[String].empty




