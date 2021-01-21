package underscore.cats.chapter2

import underscore.cats.chapter2.Semigroup.{Monoid, Semigroup}

object MonoidExercises {
  def main(args: Array[String]): Unit = {

    object Monoid {
      def apply[A](implicit monoid: Monoid[A]) = monoid
    }


      implicit def setUnionMonoid[A]: Monoid[Set[A]] = new Monoid[Set[A]] {
        override def empty: Set[A] = Set.empty[A]

        override def combine(x: Set[A], y: Set[A]): Set[A] = x.union(y)
      }

      val intSetMonoid = Monoid[Set[Int]]
      val stringSetMonoid = Monoid[Set[String]]

      println(intSetMonoid.combine(Set(1, 2), Set(2, 3)))

      implicit def setIntersectionSemigroup[A]: Semigroup[Set[A]] =
        (x: Set[A], y: Set[A]) => x intersect y

    object MonoidInstances {
      implicit val booleanAndMonoid: Monoid[Boolean] = {
        new Monoid[Boolean] {
          override def empty: Boolean = true

          override def combine(x: Boolean, y: Boolean): Boolean = x && y
        }
      }

      implicit val booleanOrMonoid: Monoid[Boolean] = {
        new Monoid[Boolean] {
          override def empty: Boolean = false

          override def combine(x: Boolean, y: Boolean): Boolean = x || y
        }
      }
    }
  }
}
