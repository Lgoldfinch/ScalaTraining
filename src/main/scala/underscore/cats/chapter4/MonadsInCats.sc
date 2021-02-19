import cats.Monad
import cats.implicits._
import cats.Id
val op1 = Monad[Option].pure(3)
val op2 = Monad[Option].flatMap(op1)(a => Some(a + 2))

Monad[List].flatMap(List(1, 2, 3))(a => List(a, a*10))

1.pure[Option]

def sumSquare[F[_]: Monad](a: F[Int], b: F[Int]): F[Int] =
  for {
    x <- a
    y <- b
  } yield x*x + y*y
sumSquare(Option(3), Option(4))
// res10: Option[Int] = Some(25)
sumSquare(List(1, 2, 3), List(4, 5))
// res11: List[Int] = List(17, 26, 20, 29, 25, 34)

sumSquare(3 : Id[Int], 4: Id[Int])

val a = Monad[Id].pure(3)

val b = Monad[Id].flatMap(a)(_ + 1)

for {
  x <- a
  y <- b
} yield x + y

