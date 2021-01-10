package underscore.cats.chapter1



object EqLesson {
  def main(args: Array[String]): Unit = {
    import cats.instances.int._
    import cats.instances.string._
    import cats.instances.option._
    import cats.Eq
    import cats.syntax.eq._

    val cat1 = Cat("Garfield", 38, "orange and black")
    val cat2 = Cat("Heathcliff", 33, "orange and black")
    val optionCat1 = Option(cat1)
    val optionCat2 = Option.empty[Cat]

   implicit val catEq: Eq[Cat] = Eq.instance[Cat] {
     (cat1, cat2) => {
       val ageEquality = cat1.age === cat2.age
       val nameEquality = cat1.name === cat2.name
       val colourEquality = cat1.color === cat2.color

       ageEquality && nameEquality && colourEquality
     }
   }

    println(cat1 === cat2)
    println(cat1 =!= cat2)

    println(optionCat1 === optionCat2)
    println(optionCat1 =!= optionCat2)

  }

}
