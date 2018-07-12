package actors

import akka.actor.SupervisorStrategy.Restart
import akka.actor.{Actor, ActorRef, OneForOneStrategy, Props, SupervisorStrategy}
import data.{ErrorResponse, OrderAPIRequest, OrderEmailRequest}

/**
  * Created by alexwerff on 11.07.18.
  */
class OrderProcessorActor extends Actor{


   override def receive: Receive = {
      case OrderProcessorActor.Initialise(availbleProcessors) => {
         //FlatMap usage since Option (Some or None) is a Monad
         val processors = availbleProcessors.flatMap{
            case "Email" => Some("Email" -> context.actorOf(EmailActor.props()))
            case "API" => Some("API" -> context.actorOf(EmailActor.props()))
            case _ => None
         }
         context.become(initialised(processors.toMap))
      }
      case _ =>
   }

   def initialised(processors:Map[String, ActorRef]):Receive = {
      case req:OrderEmailRequest => processors.get("Email").foreach(_.forward(req))
      case req:OrderAPIRequest => processors.get("API").foreach(_.forward(req))
      case _ => sender() ! ErrorResponse("Unknown processor")
   }

   override def supervisorStrategy: SupervisorStrategy = OneForOneStrategy(maxNrOfRetries = 3){
      case ex => Restart
   }
}


object OrderProcessorActor{
   case class Initialise(availbleProcessors:List[String])
   def props():Props= Props(new OrderProcessorActor())
}
