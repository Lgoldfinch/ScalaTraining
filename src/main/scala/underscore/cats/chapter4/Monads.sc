import cats.Id

/**
Anything with a flatmap method.

 For comprehensions ar syntax for monads.

 MECHANISM FOR SEQUENCING COMPUTATIONS

 Similar to functors, but they only allow complications to arise at the beginning of the sequence.

 Functors don't allow for further complications at each step in the sequence.

 Fail-fast error handling.

 Every Monad is a functor.

 Can run futures in parallel but Monads are about sequencing.
 **/

/**
DEFINITION, has two operations:

 pure = A => F[A]
 flatMap = (F[A], A => F[B]) => F[B]

 pure allows you to make a monadic context from a plain value.
 flatmap provides sequencing step -> extract value from a context -> generate the next context in the sequence.
 **/

trait Monad[F[_]] {
 def pure[A](a: A): F[A]
 def flatMap[A, B](value: F[A])(func: A => F[B]): F[B]

 def map[A, B](value: F[A])(func: A => B): F[B] =
  flatMap(value)(a => pure(func(a)))
}

def pure[A](value: A): Id[A] = value

def map[A, B](initial: Id[A])(func: A => B): Id[B] = {
func(initial)

}

def flatMap[A, B](initial: Id[A])(func: A => Id[B]): Id[B] = {
  func(initial)
}