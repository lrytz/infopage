package infopage

import akka.actor.ActorSystem
import akka.kernel.Bootable
import spray.routing.directives.DebuggingDirectives
import spray.routing.{ExceptionHandler, SimpleRoutingApp}
import scala.concurrent.Future
import akka.event.{LogSource, Logging}

import ContestSubmissionJsonSupport._
import ContestDB._
import scala.concurrent.ExecutionContext.Implicits.global

/**
 * curl -X POST --header "Content-Type:application/json" -d @test.json 127.0.0.1:8080/contestSubmission
 */
class ContestApp extends SimpleRoutingApp with Bootable with CORSSupport {

  implicit val system = ActorSystem("contest")

  val log = Logging(system, this)(new LogSource[AnyRef] {
    def genString(o: AnyRef): String = o.getClass.getName
    override def getClazz(o: AnyRef): Class[_] = o.getClass
  })

  def startup(): Unit = {
    startServer(interface = Config.host, port = Config.port) {
      cors {
        DebuggingDirectives.logRequestResponse("contestSubmission") { // set level to DEBUG in application.conf
          path("contestSubmission") {
            post {
              entity(as[ContestSubmission]) { s =>
                onSuccess(Future(insert(s))) { _ => complete("KTHXBYE") }
              }
            }
          } ~
            path("submissionsToCVS") {
              get {
                onSuccess(Future(submissionsToCVS))(data => complete(data mkString "\n"))
              }
            } ~
            path("wipeOutSubmissions") {
              post {
                onSuccess(Future(wipeOutSubmissions()))(_ => complete("VOID"))
              }
            }
        }
      }
    }
  }

  def shutdown(): Unit = ()
}
