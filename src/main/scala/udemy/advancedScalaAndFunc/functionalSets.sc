
sealed trait MySet[A] extends (A => Boolean) {

 def apply(elem: A): Boolean = contains(elem)

  def contains(elem: A): Boolean

  def +(elem: A): MySet[A]

  def ++(anotherSet: MySet[A]): MySet[A]

  def map[B](f: A => B): MySet[B]

  def flatMap[B](f: A => MySet[B]): MySet[B]

  def filter(predicate: A => Boolean ): MySet[A]

  def forEach(f: A => Unit): Unit

  def -(elem: A): MySet[A]

  def intersection(secondSet: MySet[A]): MySet[A]

 def differenceWithSet(secondSet: MySet[A]): MySet[A]

 def unary_! : MySet[A] // implement negation of a set. set[1,2,3] => Everything but [1,2,3]
}

case class Nil[A]() extends MySet[A] {

 override def contains(elem: A): Boolean = false

 override def +(elem: A): MySet[A] = Cons(elem, this)

 override def ++(anotherSet: MySet[A]) = anotherSet // union

 override def map[B](f: A => B): MySet[B] = Nil[B]

 override def flatMap[B](f: A => MySet[B]): MySet[B] = Nil[B]

 override def filter(predicate: A => Boolean): MySet[A] = this

 override def forEach(f: A => Unit): Unit = ()

 override def -(elem: A) = Nil[A]

 override def intersection(secondSet: MySet[A]): MySet[A] = Nil[A]

 override def differenceWithSet(secondSet: MySet[A]) = Nil[A]

 override def unary_! : MySet[A] = new PropertyBasedSet[A](_ => true)
}

case class Cons[A](head: A, tail: MySet[A]) extends MySet[A] {

 override def contains(elem: A): Boolean = elem == head || tail.contains(elem)

 override def +(elem: A): MySet[A] = {
  if (this contains elem) this
  else Cons(elem, this)
 }

 override def ++(anotherSet: MySet[A]): MySet[A] = tail ++ anotherSet + head

 override def map[B](f: A => B): MySet[B] = tail.map(f) + f(head) //Cons(f(head), map(tail))

 override def flatMap[B](f: A => MySet[B]): MySet[B] = tail.flatMap(f).++(f(head))

 override def filter(predicate: A => Boolean): MySet[A] = {
  val filteredTail = tail.filter(predicate)
  if (predicate(head)) filteredTail + head
  else filteredTail
 }

 override def forEach(f: A => Unit): Unit = f(head) {
  tail.forEach(f)
 }

 override def differenceWithSet(secondSet: MySet[A]) = filter(x => !secondSet(x))

 override def -(elem: A) = {
  if (head == elem) tail
  else tail - elem + head
 }

 override def intersection(secondSet: MySet[A]) = filter(secondSet)
 override def unary_! = new PropertyBasedSet[A](x => !this.contains(x))
}

// class AllInclusiveSet[A] extends MySet[A]  {
//  override def contains(elem: A) = true
//
//  override def +(elem: A) = this
//
//  override def ++(anotherSet: MySet[A]) = this
//
//
//  // allIncSet[Int] all natural numbers. The nattys.
//  // nattys.map(x => x % 3)
//  override def map[B](f: A => B) =
//
//  override def flatMap[B](f: A => MySet[B]) = ???
//
//  override def filter(predicate: A => Boolean): MySet[A] = { // property based set.
//   filter(predicate)
//  }
//
//  override def forEach(f: A => Unit): Unit = ???
//
//  override def -(elem: A) = ???
//
//  override def intersection(secondSet: MySet[A]) = filter(secondSet)
//
//  override def differenceWithSet(secondSet: MySet[A]) = filter(!secondSet)
//
//  override def unary_! = Nil[A]
// }

// all elements of type A which satisfy a property
// ( x in A | property(x))


 class PropertyBasedSet[A](property: A => Boolean) extends MySet[A] {

  override def contains(elem: A): Boolean = property(elem)


  // { x in A | property(x) } + element = { x in A | property(x) || x == element }

  override def +(elem: A): MySet[A] = new PropertyBasedSet[A](x => property(x) || x == elem)

  // { x in A | property(x) } ++ set = { x in A | property(x) || set contains x }

  override def ++(anotherSet: MySet[A]) = new PropertyBasedSet[A](x => property(x) || anotherSet(x))

  override def map[B](f: A => B) = politelyFail

  override def flatMap[B](f: A => MySet[B]) = politelyFail

  override def filter(predicate: A => Boolean) = new PropertyBasedSet[A](x=> property(x) && predicate(x))

  override def forEach(f: A => Unit): Unit = ???

  override def -(elem: A) = filter(x => x != elem)

  override def intersection(secondSet: MySet[A]) = filter(secondSet)

  override def differenceWithSet(secondSet: MySet[A]) = filter(!secondSet)

  override def unary_! = new PropertyBasedSet[A](x => !property(x))

  def politelyFail = throw new IllegalArgumentException("Really deep rabbit hole!")
 }


// List(1,2) && List(2,3) == List(3)
// inter  == List(2)

