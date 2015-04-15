package infopage

import spray.http._
import spray.http.HttpHeaders._
import spray.routing._
import spray.http.StatusCodes._

// http://stackoverflow.com/questions/1256593/jquery-why-am-i-getting-an-options-request-instead-of-a-get-request
// https://developer.mozilla.org/en-US/docs/Web/HTTP/Access_control_CORS
trait CORSSupport {
  this: HttpService =>

  private val allowOriginHeader = `Access-Control-Allow-Origin`(AllOrigins)
  private val optionsCorsHeaders = List(
    `Access-Control-Allow-Headers`("Origin, X-Requested-With, Content-Type, Accept, Accept-Encoding, Accept-Language, Host, Referer, User-Agent"),
    `Access-Control-Max-Age`(1728000)
  )

  def cors[T]: Directive0 = mapRequestContext { ctx =>
    ctx.withRouteResponseHandling {
      // OPTION request for a resource that responds to other methods
      case Rejected(x) if ctx.request.method.equals(HttpMethods.OPTIONS) && x.exists(_.isInstanceOf[MethodRejection]) => {
        val allowedMethods: List[HttpMethod] = x.collect { case rejection: MethodRejection => rejection.supported }
        ctx.complete {
          HttpResponse().withHeaders(
            `Access-Control-Allow-Methods`(HttpMethods.OPTIONS, allowedMethods :_*) ::  allowOriginHeader ::
              optionsCorsHeaders
          )
        }
      }
    }.withHttpResponseHeadersMapped { headers =>
      allowOriginHeader :: headers
    }
  }

  override def timeoutRoute = complete {
    HttpResponse(
      InternalServerError,
      HttpEntity(ContentTypes.`text/plain(UTF-8)`, "The server was not able to produce a timely response to your request."),
      List(allowOriginHeader)
    )
  }
}
