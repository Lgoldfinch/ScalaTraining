package underscore.cats.chapter1

import underscore.cats.chapter1.TypeClassInstance.Person

class TypeClassUse {
import TypeClassInstance.JsonWriterInstances._
  /**
   * Type class use is any functionality that requires an instance to work.
   *
   * Interface objects, place methods inside singleton objects.
   */


  object Json {
    def toJson[A](value: A)(implicit w: JsonWriter[A]): Json =
      w.write(value)
  }

  Json.toJson(Person("Louis", "bosh@gmail.com"))

  /**
   * Interface syntax
   *
   * we can use extension methods to extend existing types with interface methods. Cats refers to this as syntax for  type class.
   */

  object JsonSyntax {
    implicit class JsonWriterOps[A](value: A) {
      def toJson(implicit w: JsonWriter[A]): Json =
        w.write(value)
    }
  }

  /**
   * Use interface syntax by importing it alongside instances for the types needed.
   */
import JsonSyntax._
  Person("Louis", "bosh@gmail.com").toJson

  /**
   * Implicitly method
   *
   * Summon a value from implicit scope.
   */

  implicitly[JsonWriter[String]] // <--- good for debugging

  /** Cannot have implicit at top level because of implicit scope.
   where compiler searches for candidate instances.
   */

  /**
   * 4 ways to package type class instances
   * 1) in an object, like JsonWriterInstances. Into scope by importing.
   * 2) traits. Brings instance into scope via inheritance.
   * 3) Companion object for type class.
   * 4) placing them in companion object of parameter type
   *
   * Best to use 3/4, then the implicit is brought into scope by default.
   */
}
