package capgemWorkshop

trait Equality[A] {
  def eq(a1: A, a2: A): Boolean
}

object Equality {
  def apply[A](implicit instance: Equality[A]): Equality[A] = instance

  implicit def intEquality: Equality[Int] = (a1: Int, a2: Int) => a1 == a2
}

