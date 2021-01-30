
/**
  Get instances by using the Functor.apply method on companion object.
 **/

import cats.Functor
import cats.instances.list._
import cats.instances.option._

val list1 = List(1, 2, 3)

val list2 = Functor[List].map(list1)(_ * 2)

val option1 = Option(123)

val option2 = Functor[Option].map(option1)(_.toString)

/**
 Provides LIFT = converts a function of type A => B to one that operates over a functor and has type F[A] => F[B]
 **/

val func = (x: Int) => x + 1

val liftedFunc = Functor[Option].lift(func)
liftedFunc(Option(2))

/**
Provides AS = Replaces value inside the functor with the given value
 **/

Functor[List].as(list1, "AS")

/**
Provides LIFT = converts a function of type A => B to one that operates over a functor and has type F[A] => F[B]
 **/

import cats.instances.function._ // for Functor
import cats.syntax.functor._ // for map

val func1 = (a: Int) => a + 1
val func2 = (a: Int) => a * 4
val func3 = (a: Int) => a.toString

val func4 = func1.map(func2).map(func3)

func4(123)

def doMaths[F[_]](start: F[Int])(implicit functor: Functor[F]): F[Int] = start.map(n => n + 1 * 2)

doMaths(Option(20))  // so if you want to treat different data types the same way you can use a functor

doMaths(List(1, 2, 3))

