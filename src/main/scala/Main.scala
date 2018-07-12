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
    
      system.actorOf(RESTActor.props(), "RESTActor")
   }
}
