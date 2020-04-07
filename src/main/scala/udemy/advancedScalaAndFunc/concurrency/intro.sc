object Intro {

  // JVM threads
  val aThread = new Thread(new Runnable {
    override def run() = println("running In parallel")
  })

  // create a jvm thread =? OS thread

}

Intro.aThread.start