package RedBook

import java.util.concurrent.{ExecutorService, Future}

import org.apache.commons.lang3.time.StopWatch
//import org.apache.commons.lang3.time.StopWatch


object Chap4 extends App {

  import java.util.concurrent._

  type Par[A] = ExecutorService ⇒ Future[A]

  case class UnitFuture[A](get: A) extends Future[A] {
    override def cancel(mayInterruptIfRunning: Boolean): Boolean = false

    override def isCancelled: Boolean = false

    override def isDone: Boolean = true

    override def get(timeout: Long, unit: TimeUnit): A = get
  }



  def unit[A](a: A): Par[A] = _ ⇒ UnitFuture(a)

  def run[A](es: ExecutorService)(a: Par[A]): Future[A] = a(es)

  def map2[A, B, C](a: Par[A], b: Par[B])(f: (A, B) => C): Par[C] = { // par takes executor => gives Future
    es => UnitFuture(f(a(es).get, b(es).get))
  }

  def time[A](a: ⇒ A): (A, Double) = {
    val s = StopWatch.createStarted()
    val r = a
    s.stop()
    println(f"$a -> ${s.getTime/1000d}%2.3f")
    r → (s.getTime / 1000d)
  }


  def sequence[A](ps: List[Par[A]]): Par[List[A]] = {
    ps.foldLeft(unit(List.empty[A])) {
      (acc, v) => map2(acc, v)(_ :+ _)
    }
  }

  def fork[A](a: ⇒ Par[A]): Par[A] = es ⇒ {
    es.submit(new Callable[A] {
      override def call(): A = a(es).get
    })
  }

  def map[A,B](a: Par[A])(f: A => B): Par[B] = map2(a, unit(()))((a, _) => f(a))

  def lazyUnit[A](a: ⇒ A): Par[A] = fork(unit(a))

  def asyncF[A, B](f: A ⇒ B): A ⇒ Par[B] = a ⇒ lazyUnit(f(a))

  def compute(a: Int) = { Thread.sleep(1000); 1000 * a}

  val es = Executors.newFixedThreadPool(10)

  def parFilter[A](l: List[A])(f: A => Boolean): Par[List[A]] = {
//    unit(l.filter(f))
   val r: List[Par[Option[A]]] = l.map { asyncF(v => if (f(v)) Some(v) else None)}
    val rAsParWithOption: Par[List[Option[A]]] = sequence(r)
    map(rAsParWithOption)(_.flatten)

  }
    time(parFilter(List(1,2,3,4))( x => compute(x) / 1000 % 2 == 0)(es).get)

  def reducePar(ints: IndexedSeq[Int])(combine: (Int, Int) => Int, zero: Int): Par[Int] =
    ints.foldLeft(zero)((a , acc) =>
//    unit(ints.foldLeft(zero)(combine))

  def time[A](a: ⇒ A): (A, Double) = {
    val s = StopWatch.createStarted()
    val r = a
    s.stop()
    println(f"$a -> ${s.getTime / 1000d}%2.3f")
    r → (s.getTime / 1000d)
  }
}

