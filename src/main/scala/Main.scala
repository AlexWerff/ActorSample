import akka.actor.ActorSystem
import akka.stream.ActorMaterializer

/**
  * Created by alexwerff on 11.07.18.
  */
class Main {
   def main(args:Array[String]):Unit={
      implicit val system = ActorSystem("modelbackend")
      implicit val materializer = ActorMaterializer()
      implicit val executionContext = system.dispatcher
    
      val processorActor = system.actorOf(OrderProcessorActor.props(), "OrderProcessorActor")
      val restActor = system.actorOf(RESTActor.props(), "RESTActor")
      restActor ! RESTActor.Initialise(processorActor)
   }
}
