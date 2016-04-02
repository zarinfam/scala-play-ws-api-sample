package com.example

import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import play.api.libs.ws.StreamedResponse
import play.api.libs.ws.ning.NingWSClient
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

object WebClient {
  def main(args: Array[String]): Unit = {
//    playWsGet()
    playWsStream()
  }

  def playWsGet() {
    implicit val system = ActorSystem("playWsGet")
    implicit val materializer = ActorMaterializer()
    // Instantiation of the client
    // In a real-life application, you would instantiate one, share it everywhere,
    // and call wsClient.close() when you're done
    val wsClient = NingWSClient()
    wsClient
      .url("http://akka.io")
      .get()
      .map { wsResponse =>
        if (!(200 to 299).contains(wsResponse.status)) {
          sys.error(s"Received unexpected status ${wsResponse.status} : ${wsResponse.body}")
        } else {
          println(wsResponse.body)
        }
      }
      .onComplete(a => println("complete playWsGet method"))
  }

  def playWsStream() {
    import play.api.libs.ws.ning.NingWSClient
    import scala.concurrent.ExecutionContext.Implicits.global
    implicit val system = ActorSystem("playWsStream")
    implicit val materializer = ActorMaterializer()

    // Instantiation of the client
    // In a real-life application, you would instantiate one, share it everywhere,
    // and call wsClient.close() when you're done
    val wsClient = NingWSClient()

    // Make the request
    val futureResponse: Future[StreamedResponse] =
      wsClient.url("http://akka.io")
        .withMethod("GET").stream()

    futureResponse.flatMap {
      res => res.body.runForeach(b => println(b.decodeString("UTF-8")))
    }.onComplete(a => println("complete playWsStream method"))


  }

}
