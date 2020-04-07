package udemy.akka

import akka.actor.SupervisorStrategy.{Restart, Stop}
import akka.actor.{Actor, ActorLogging, ActorSystem, OneForOneStrategy, PoisonPill, Props, Stash, SupervisorStrategy}

object Intro extends App {

  class SimpleActor extends Actor with Stash with ActorLogging {

    def anotherHandler: Receive = {
      case message => println(s"in another receive handler: $message")
    }

    override def receive: Receive = {
      case "createChild" =>
        val childActor = context.actorOf(Props[SimpleActor], "myChild")
        childActor ! "bello"
      case "stashThis" => stash()
      case "change handler now" => unstashAll()
        context.become(anotherHandler)
      case "change" => context.become(anotherHandler)
      case message => println(s"I received: $message")
    }


    override def preStart(): Unit = {
      println("I'm starting")
    }

    override def supervisorStrategy: SupervisorStrategy = OneForOneStrategy() {
      case _: RuntimeException => Restart
      case _ => Stop

    }
  }
  // actor encapsulation. You cant instantiate an actor by itself it needs to be done by a system.
  val system = ActorSystem("AkkaRecap")
  val actor = system.actorOf(Props[SimpleActor], "simpleActor")
  // can comm with actors by sending messages, using tell/ask methods.
  actor ! "hello" // tell

      /*
      - messages are sent asynchronously
      - many actors (in the millions) can share a few dozen threads.
      - each message is processed or handled atomically. You dont need to lock any resources.
       */

      // changing actor behaviour + stashing, do this by mixing in the stash trait
     // actors can spawn  other actors.
    // Akka provides 3 top level parents actors = guardians. /system, /user, / = root guardian    <- parent of all actors.
    // have a defined lifecycle / started / stopped / suspended / resumed / restarted
    // stopping actors - context.stop or
  // can use Actor logging trait to log instead of pl.
  actor ! PoisonPill

  // supervision decides how parent actors response to child actor failures
// akka infrastructure = dispatchers, routers, mailers


}
