package actors

import akka.actor.{Actor, Props}
import data._

/**
  * Created by alexwerff on 11.07.18.
  */
class APIActor extends Actor{
   override def receive: Receive = {
      case APIActor.Connect(apiEndpoint) => context.become(connected(apiEndpoint))
      case _ => sender() ! APIActor.NotConnectedError()
   }

   def connected(apiEndpoint: ApiEndpoint):Receive={
      case OrderMessage(order) => {
         //DO API ENDPOINT STUFF (FUTURE OR WHATEVER)

         sender() ! OrderProcessedMessage(order)
      }
      case _=> sender() ! ErrorResponse("Unknown Message") //UNKNOWN MESSAGE
   }

}

object APIActor{
   case class Connect(apiEndpoint: ApiEndpoint)
   case class NotConnectedError()

   def props():Props= Props(new APIActor())
}
