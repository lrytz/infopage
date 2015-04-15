package infopage

import spray.httpx.SprayJsonSupport
import spray.json.DefaultJsonProtocol

case class Contact(first: String, last: String, city: String, phone: String, email: String)
case class ContestSubmission(contact: Contact, answers: List[String])

object ContestSubmissionJsonSupport extends DefaultJsonProtocol with SprayJsonSupport {
  implicit val ContactFormat = jsonFormat5(Contact)
  implicit val ContestSubmissionFormat = jsonFormat2(ContestSubmission)
}
