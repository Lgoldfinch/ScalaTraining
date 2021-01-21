package underscore.cats.chapter2

import underscore.cats.chapter2.Semigroup.Monoid

object Monoid {

  /**
   * Allow us to add or combine values
   *
   * addition of integers is CLOSED = adding two ints always produces an int.
   *
   * Identity element 0 with this property:
   *
   * a + 0 == 0 + a == a
   *
   * Addition is ASSOCIATIVE = order is irrelevant.
   *
   * Same applies for multiplication, if 1 is identity. Also ASSOCIATIVE.
   *
   */

  /**
   * MONOID DEFINITION:
   *
   * Examples above are monoids.
   *
   * An operation combine with type (A, A) => A
   * an element empty of type A.
   *
   * below is simplified version from cats

   */

  def associativeLaw[A](x: A, y: A, z: A)
                       (implicit m: Monoid[A]): Boolean = {
    m.combine(x, m.combine(y, z)) ==
      m.combine(m.combine(x, y), z)
  }
  def identityLaw[A](x: A)
                    (implicit m: Monoid[A]): Boolean = {
    (m.combine(x, m.empty) == x) &&
      (m.combine(m.empty, x) == x)
  }


  /**
   * subtraction isn't a monoid as it isn't associative. ie
   *
   * (1-2) - 3 = -4
   *
   * 1 - (2 - 3) = 2
   */
}
