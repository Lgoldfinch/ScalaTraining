val simpleAddFunction = (x: Int, y: Int) => x + y
def simpleAddMethod(x: Int, y: Int) = x + y
def curriedAddMethod(x: Int)(y: Int) = x + y

val a: Int => Int = {
  curriedAddMethod(7)
}

val b = {
  curriedAddMethod(7) _
}

val d = (x: Int) => simpleAddFunction(7,x)
// add7: Int => Int = y => 7 + y

val e = simpleAddFunction.curried(7)

def curriedFormatter(s: String)(number: Double): String = s.format(number)
val numbers = List(Math.PI,  Math.E, 1, 9.8)

val simpleFormat: Double => String = curriedFormatter("%4.2f") _
val seriousFormat = curriedFormatter("%8.6f") _
val preciseFormat = curriedFormatter("%14.12f") _

 numbers.map(simpleFormat)
numbers.map(seriousFormat)
numbers.map(preciseFormat)

println(numbers.map(curriedFormatter("%14.12f")))


def byName(n: => Int): Int = n + 1

def byFunction(f: () => Int) = f() + 1

def method: Int = 42
def parentMethod(): Int = 42

byFunction(() => 4)
byFunction(parentMethod)