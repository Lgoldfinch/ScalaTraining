import cats.Functor
import cats.instances.function._
import cats.syntax.functor._
import cats.instances.list._
import cats.instances.option._


val func1: Int => Double = (x: Int) => x.toDouble

val func2: Double => Double = (y: Double) => y * 2

(func1 map func2)(1)

func2(func1(1))

  val f = (x : Int) => x * 2

  val f2 = f andThen f

val list1 = List(1,2,3)

val list2a = list1.map(_ * 2)
val list2 = Functor[List].map(list1)(_ * 2)

val option1 = Option(123)

val option2 = Functor[Option].map(option1)(_.toString)
val option2a = option1.map(_.toString)

val func = (x: Int) => x + 1

val liftedFunc = Functor[Option].lift(func)

def doMaths[F[_]](start: F[Int])(implicit functor: Functor[F]): F[Int] =
  start.map(n => n + 1 * 2)

sealed trait Tree[+A]

final case class Branch[A](left: Tree[A], right: Tree[A]) extends Tree[A]

final case class Leaf[A](value: A) extends Tree[A]

object AnObject {

  implicit val treeFunctor: Functor[Tree] =
    new Functor[Tree] {
       def map[A, B](tree: Tree[A])(f: A => B): Tree[B] = tree match {
         case Branch(left,right) => Branch(map(left)(f), map(right)(f))
         case Leaf(a) => Leaf(f(a))
       }
    }
}

object Tree {
  def branch[A](left: Tree[A], right: Tree[A]): Tree[A] = Branch(left,right)

  def leaf[A](value: A): Tree[A] = Leaf(value)
}

import AnObject._

Tree.leaf(100).map(_ * 2)

