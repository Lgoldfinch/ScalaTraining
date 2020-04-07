package udemy.advancedScalaAndFunc

import scala.annotation.tailrec

abstract class MyStream[+A] {
    def isEmpty: Boolean
    def head: A
    def tail: MyStream[A]

    def #::[B >: A](element: B): MyStream[B]    //prepends element of type a to a stream
    def ++ [B >:A](anotherStream: => MyStream[B]): MyStream[B]

    def foreach(f: A => Unit): Unit
    def map[B](f: A => B): MyStream[B]
    def flatmap[B](f: A => MyStream[B]): MyStream[B]
    def filter(predicate: A => Boolean): MyStream[A]

    def take(n: Int): MyStream[A]
    def takeAsList(n: Int): List[A] = take(n).toList()

    @tailrec
   final def toList[B >: A](acc: List[B] = Nil): List[B] =
      if (isEmpty) acc
      else tail.toList(head :: acc)
   }

object EmptyStream extends MyStream[Nothing] {

  override def isEmpty: Boolean = true

  override def head: Nothing = throw new NoSuchElementException

  override def tail: MyStream[Nothing] = throw new NoSuchElementException

  override def #::[B >: Nothing](element: B): MyStream[B] = new ConsStream(element, this)

  override def ++[B >: Nothing](anotherStream: => MyStream[B]): MyStream[B] = anotherStream

  override def foreach(f: Nothing => Unit): Unit = ()

  override def map[B](f: Nothing => B): MyStream[B] = this

  override def flatmap[B](f: Nothing => MyStream[B]): MyStream[B] = this

  override def filter(predicate: Nothing => Boolean): MyStream[Nothing] = this

  override def take(n: Int): MyStream[Nothing] = this

  override def takeAsList(n: Int): List[Nothing] = Nil
}

  class ConsStream[+A](hd: A, t: => MyStream[A]) extends MyStream[A] {

   override def isEmpty: Boolean = false

   override val head: A = hd

   override lazy val tail: MyStream[A] = t // call by need

   override def #::[B >: A](element: B): MyStream[B] = new ConsStream(element, this)

   override def ++[B >: A](anotherStream: => MyStream[B]): MyStream[B] = new ConsStream(head, tail ++ anotherStream) // is this not recursive? cant see no symbol.

   override def foreach(f: A => Unit): Unit = {
     f(head)
     tail.foreach(f)
   }

   override def map[B](f: A => B): MyStream[B] = new ConsStream(f(head), tail.map(f))

   override def flatmap[B](f: A => MyStream[B]): MyStream[B] = f(head) ++ tail.flatmap(f)

   override def filter(predicate: A => Boolean): MyStream[A] =
     if (predicate(head)) new ConsStream(head, tail.filter(predicate))
    else tail.filter(predicate)

   override def take(n: Int): MyStream[A] =
     if (n <= 0) EmptyStream
    else if (n == 1) new ConsStream(head, EmptyStream)
    else new ConsStream(head, tail.take(n-1))
  }

@tailrec
object MyStream {
  def from[A](start: A)(generator: A => A): MyStream[A] =
    new ConsStream(start, MyStream.from(generator(start))(generator))
}

object AASD extends App {

  val naturals = MyStream.from(1)(_ + 1)
  println(naturals.tail.head)

  // stream of fibonacci numbers.
// a stream will be comprised of a Cons(integer, Cons(...) ) or Cons(integer, Empty)
// 1 , 1 , 2 , 3, 5


  def fib(first: Int , second: Int): MyStream[Int] =
    new ConsStream(first, fib(second, first + second))

//def eratosthenes(numbers: MyStream[Int]): MyStream[Int] =
//  if(numbers.isEmpty) numbers
//  else new ConsStream(numbers.head, )


//  val fibonacciNumbers = MyStream.from(1)(x => x  )
//
//  // use
//  def fibonacciStream(acc: Int = 0): MyStream[Int] =
//    // Cons(1 , Cons(1 , Cons(2 , Cons(3 , 5))))
//     MyStream.from(1)(x => {
//
//     })


//  val startFrom2 = 2 #:: naturals.filter(x => x % 2 != 0 && x % 3 &&  0 )


}

// naturals MyStream.from(1)(x => x + 1) = stream of nat numbers (potentially infinite)
// naturals.take(100) // lazily evaluated stream of the first 100 naturals ( finite stream)