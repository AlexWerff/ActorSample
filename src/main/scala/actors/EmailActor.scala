package actors

import akka.actor.{Actor, Props}
import data._

/**
  * Created by alexwerff on 11.07.18.
  */
class EmailActor extends Actor{
   override def receive: Receive = {
      case EmailActor.Connect(emailConfig) => context.become(connected(emailConfig))
      case _ => sender() ! APIActor.NotConnectedError()
   }

   def connected(emailConfig:EmailConfig):Receive ={
      case OrderMessage(order) =>{
         //THIS FAILS FOR SOME REASONS
         sender() ! OrderProcessFailed(order)
      }
      case _=> sender() ! ErrorResponse("Unknown Message") //UNKNOWN MESSAGE
   }
}

object EmailActor{
   case class Connect(emailConfigs: EmailConfig)
   case class NotConnectedError()

   def props():Props= Props(new EmailActor())
}

