package data

/**
  * Created by alexwerff on 11.07.18.
  */
case class Order(articleID:String, quantity:Int, customerID:String)

case class EmailConfig(smtp:String)
case class ApiEndpoint(url:String)
