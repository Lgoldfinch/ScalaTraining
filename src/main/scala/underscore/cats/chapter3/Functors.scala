package underscore.cats.chapter3

object Functors {
import cats.instances.function._
import cats.syntax.functor._


  /**
   *informally, anything with a map method.
   *
   * All values in list affected at once.
   *
   * Wrapper remains unchanged.
   *
   * Futures aren't good for FP as they aren't referentially transparent.
   *
   * Single argument functions are also functors.
   *
   * To coerce them to the correct shape, fix parameter type and let result type vary.
   */

  val func1: Int => Double = (x: Int) => x.toDouble

  val func2: Double => Double = (y: Double) => y * 2

  func1.map(func2)(1)
  func1.andThen(func2)(1)
  func2(func1(1))

    /**
     Calling map doesn't run any of the operations, but if we pass an argument to the final function all of the
      operations are run in sequence. Lazily queues up operations, like a future.
     */

  /**
   * FUNCTOR DEFINITION
   * A Functor is a type F[A] with an operation map with type (A => B) => F[B]
   */

  /**
   * FUNCTOR LAWS
   * Guarantee same semantics whether we sequence many small operations one by one, or combine them into a larger
   * function before mapping. Need the following laws to hold:
   *
   * IDENTITY: calling map with the identity function is the same as doing nothing =
   * fa.map(a => a) == fa
   *
   * COMPOSITION: mapping with two functions f and g is the same as mapping with f and then mapping with g =
   * fa.map(g(f(_))) == fa.map(f).map(g)
   */

  /**
   * Regular types have no holes, type constructors have holes we can fill to produce types.
   *
   * List is a type constructor. Can fill with a parameter to produce a regular type.
   */

}
