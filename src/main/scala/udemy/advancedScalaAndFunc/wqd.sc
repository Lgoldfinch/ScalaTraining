class Lazy[+A](value: => A) {

  private lazy val internalValue = value
  def use: A = value
  def flatMap[B](f: => A => Lazy[B]): Lazy[B] = f(internalValue)
}

object Lazy {
  def apply[A](value: => A): Lazy[A] = new Lazy(value)
}

val lazyInstance = Lazy {
  println("bosh")
  42
}

val flatmappedIns = lazyInstance.flatMap(x => Lazy {
  10 * x
})

val flatmappedIns2 = lazyInstance.flatMap(x => Lazy {
  10 * x
})

flatmappedIns.use
flatmappedIns2.use

trait Monad[T] {

}
