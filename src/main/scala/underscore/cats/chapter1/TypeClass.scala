package underscore.cats.chapter1

  /**
   * Three components to type class pattern,
   * Type class itself
   * Instances for particular types
   * Methods that use type classes.
   *
   * Implemented using implicit values and parameters.(can be done with implicit classes)
   *
   * traits: type classes
   * implicit values: type class instances
   * implicit parameters: type class use
   * implicit classes
   */

  /**
   * Definition: A type class is an interface or API that represents some functionality we want to implement.
   *
   * A trait that has at least one type parameter.
   */

  sealed trait Json

  final case class JsObject(get: Map[String, Json]) extends Json

  final case class JsString(get: String) extends Json

  final case class JsNumber(get: Double) extends Json

  final case object JsNull extends Json

  // The "serialize to JSON" behaviour is encoded in this trait
  trait JsonWriter[A] { // <--- JSONWriter is type class.
    def write(value: A): Json
  }

