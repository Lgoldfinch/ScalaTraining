import scala.annotation.tailrec


sealed trait MyList[+A]
case object Nil2 extends MyList[Nothing]
case class Cons2[+A](head: A, tail: MyList[A]) extends MyList[A] {

  def setHead[A](newHead: A, l: MyList[A]): MyList[A] = l match {
    case Nil2 => Nil2
    case Cons2(_, t) => Cons2(newHead, t)
  }

  def drop[A](l: MyList[A], n: Int): MyList[A] =
    if(n <= 0) l
    else l match {
    case Nil2 => Nil2
    case Cons2(_, t) => drop(t, n - 1)
  }

  def dropWhile[A](l: MyList[A], f: A => Boolean): MyList[A] = l match {
    case Cons2(h,t) if f(h) => dropWhile(t, f)
    case _ => l
  }

  def init[A](l: MyList[A]): MyList[A] = dropWhile(l, (list: Cons2[A]) => list.head == Nil2)
}
