package underscore.cats.chapter2

object Semigroup {
  /**
   * just the combine part of the monoid. This is used when it's not sensible to define an empty element.
   */

  trait Semigroup[A] {
    def combine(x: A, y: A): A
  }
  trait Monoid[A] extends Semigroup[A] {
    def empty: A
  }

  /**
   * If you define a monoid for [A] you get a Semigroup for free.
   *
   * Also, if a method needs a parameter of type Semigroup[B], you can pass Monoid[B] instead.
   *
   * ( Monoid -> Semigroup )
   */
}
