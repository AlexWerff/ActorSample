package data

/**
  * Created by alexwerff on 11.07.18.
  */
sealed trait Message

case class OrderMessage(order: Order)
case class OrderProcessedMessage(order:Order)
case class OrderProcessFailed(order:Order)
