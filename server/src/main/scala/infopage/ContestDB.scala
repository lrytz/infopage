package infopage

import com.mongodb.casbah.MongoClient
import com.novus.salat._
import com.novus.salat.global._
import com.mongodb.casbah.Imports._
import com.novus.salat.dao.SalatDAO

/**
 * Casbah Tutorial
 *   http://mongodb.github.io/casbah/tutorial.html
 *
 * Salat
 *   https://github.com/novus/salat/wiki
 *   https://github.com/novus/salat/wiki/SalatDAO
 */
object ContestDB {
  object ContestSubmissionDAO extends SalatDAO[ContestSubmission, Int](collection = MongoClient()(Config.dbName)("contestSubmission"))

  def insert(submission: ContestSubmission): Unit = {
    ContestSubmissionDAO.insert(submission)
  }

  def submissionsToCVS: List[String] = {
    val submissions = ContestSubmissionDAO.collection.toList.map(grater[ContestSubmission].asObject(_))
    def esc(s: String) = "\"" + s.replace("\"", "\\\"") + "\""
    submissions.map(s => {
      val c = s.contact
      s"""${esc(c.first)},${esc(c.last)},${esc(c.email)},${s.answers.map(esc).mkString(",")}"""
    })
  }

  def wipeOutSubmissions(): Unit = {
    ContestSubmissionDAO.collection.remove(MongoDBObject())
  }
}