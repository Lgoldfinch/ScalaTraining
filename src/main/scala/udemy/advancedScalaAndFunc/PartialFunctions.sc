


val dumbChatBot: PartialFunction[String, String] = {
case "a" => "b"
}

val aManualFussyFunction = new PartialFunction[Int, Int] {
  override def isDefinedAt(x: Int): Boolean = x == 1 || x == 2 || x == 5

  override def apply(v1: Int): Int = v1 match {
    case 1 => 4
    case 2 => 45
    case 5 => 12
  }
}

val chatBot: PartialFunction[String, String] = {
  case "hello" => "do one"
  case "yooo" => "k"
}

scala.io.Source.stdin.getLines().foreach(line => println(chatBot(line)))