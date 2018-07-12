package data

/**
  * Created by alexwerff on 11.07.18.
  */
sealed trait Request

case class AuthenticationRequest(username:String, password:String) extends Request
case class OrderEmailRequest(order: Order) extends Request
case class OrderAPIRequest(order: Order) extends Request
