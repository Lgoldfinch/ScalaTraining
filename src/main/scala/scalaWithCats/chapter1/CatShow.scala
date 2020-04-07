package scalaWithCats.chapter1

//import cats.Show
//import cats.instances.all._
//import cats.kernel.Eq
//import cats.syntax.show._
//import cats.syntax.eq._
//
//final case class Cat(name: String, age: Int, colour: String)
//
//  object Main extends App {
//
//
//
//    implicit val showCat = Show.show[Cat] { cat =>
//      val name = cat.name.show
//      val age = cat.age.show
//      val color = cat.colour.show
//      s"$name is a $age year-old $color cat."
//    }
//      implicit val catEq: Eq[Cat] = Eq.instance[Cat] {
//        (cat1, cat2) =>
//          (cat1.age === cat2.age) &&
//            (cat1.name === cat2.name) &&
//            (cat1.colour === cat2.colour)
//    }
//  }



