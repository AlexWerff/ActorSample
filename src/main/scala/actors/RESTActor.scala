package actors

import akka.actor.{Actor, Props}
import data._

/**
  * Created by alexwerff on 11.07.18.
  */
class RESTActor extends Actor{
   override def receive: Receive = {
      case AuthenticationRequest(username, password) => {
         //DO AUTH CHECK
         context.become(authenticated("API_KEY"))
      }
      case _ => sender() ! AuthenticationErrorResponse()
   }

   def authenticated(apiKey:String):Receive ={
      case OrderEmailRequest(order) =>
      case OrderAPIRequest(order) =>
      case _=> sender() ! ErrorResponse("Invalid content")
   }
}

object RESTActor{
   case class Initialise()
   def props():Props= Props(new RESTActor())
}
