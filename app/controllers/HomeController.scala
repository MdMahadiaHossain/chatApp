package controllers


import akka.stream.scaladsl
import javax.inject.{Inject, Singleton}
import play.api.http.ContentTypes
import play.api.libs.EventSource
import play.api.libs.iteratee.Concurrent
import play.api.libs.iteratee.streams.IterateeStreams
import play.api.libs.json.JsValue
import play.api.mvc._

import scala.concurrent.ExecutionContext

@Singleton
class HomeController @Inject()(cc:MessagesControllerComponents)(implicit executionContext: ExecutionContext) extends MessagesAbstractController(cc){

val (enumerator, channel) = Concurrent.broadcast[JsValue]


  def index: Action[AnyContent] = Action {
    implicit request =>
    Ok(views.html.index())
  }


  def pushStream: Action[AnyContent] = Action {

    val source = scaladsl.Source.fromPublisher(IterateeStreams.enumeratorToPublisher(enumerator))
    Ok.chunked(source.via(EventSource.flow)).as(ContentTypes.EVENT_STREAM)
  }

  def inputToChannel: Action[JsValue] = Action(parse.json) {
    request =>
      println("inputToChannel called")
      channel.push(request.body)
      Ok
  }

}
