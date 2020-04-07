
def fibonacci(n: Int): Int = { // 3
  @annotation.tailrec
  def bosh(firstNum: Int = 0, secondNum: Int = 1, n: Int): Int =
    // 0, 1, 1, 2, 3, 5, 8
    if (n <= 0) secondNum
    else bosh(secondNum, firstNum + secondNum, n - 1)
    bosh(0,1,n)

}
// Implement isSorted, which checks whether an Array[A] is sorted according to a given comparison function:
def isSorted[A](as: Array[A], ordered: (A,A) => Boolean): Boolean = {
  @annotation.tailrec
  def sorter(n: Int): Boolean = {
    if (as.length - 1 <= n) true
    else if (ordered(as(n), as(n + 1))) false
    else sorter(n + 1)
  }
  sorter(0)
}

isSorted(Array(1,3,1), (a: Int,b: Int) => a > b )
// if we want n = 3 we need

def partial1[A,B,C](a: A, f: (A,B) => C): B => C = b => f(a, b)

def curry[A,B,C](f: (A, B) => C): A => (B => C) = a => b => f(a,b)

// You have to take an argument of type `A` and return a function of type `B => C`.
// That function has to take an argument of type `B` and return a value of type `C`. Follow the types.

def uncurry[A,B,C](f: A => B => C): (A, B) => C = (a: A, b: B) => f(a)(b)

def compose[A,B,C](f: B => C, g: A => B): A => C = (a) => f(g(a))

