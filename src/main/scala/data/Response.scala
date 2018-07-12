package data

/**
  * Created by alexwerff on 11.07.18.
  */
sealed trait Response

case class AuthenticationErrorResponse()
case class OrderSuccessResponse(order: Order)
case class ErrorResponse(message:String)