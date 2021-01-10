package underscore.cats.chapter1

object TypeClassInstance {
  /**
   * Type class instances provide concrete implementations of the type class for types we care about.
   * Define instances by creating "         "  of type class and tag them with implicit.
   */

  final case class Person(name: String, email: String)

  object JsonWriterInstances {
    implicit val stringWriter: JsonWriter[String] =
      (value: String) => JsString(value)

    implicit val personWriter: JsonWriter[Person] =
      (value: Person) => JsObject(Map(
        "name" -> JsString(value.name),
        "email" -> JsString(value.email)
      ))
  }




}
