package RedBook

import scala.annotation.tailrec

object ASFD extends App {

  def isSorted[A](as:Array[A], gt: (A,A) => Boolean): Boolean = {
      @tailrec
    def go( acc: Int): Boolean = {
        if (acc >= as.length -1) true
        else if (!gt(as(acc), as(acc+1))) false
        else go(acc + 1)
      } // iterate through the array. If the comparison fails, return false.
    go(0)
  }

  def partial1[A,B,C](a: A, f: (A,B) => C): B => C =
       b => f(a,b)

  def curry[A, B, C ](f: (A, B) => C): A => B => C =
    a => b => f(a,b)

  def uncurry[A, B, C](f: A => B => C): (A, B) => C =
    (a: A, b: B) => f(a)(b)

  def compose[A, B, C](f: B => C, g: A => B): A => C =
   a => f(g(a))


}