package underscore.cats.chapter1

 object RecursiveImplicitResolution {
  /**
   * We can define instances in two ways:
   * 1) Define concrete instances as implicit vals (or object)
   * 2) Define implicit methods to construct instances from other type class instances.
   *
   */
  implicit def optionWriter[A]
  (implicit writer: JsonWriter[A]): JsonWriter[Option[A]] =
   {
    case Some(aValue) => writer.write(aValue)
    case None => JsNull
   }
 }