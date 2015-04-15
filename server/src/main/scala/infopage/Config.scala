package infopage

import com.typesafe.config.ConfigFactory

object Config {
  val config = ConfigFactory.load()
  def host = config.getString("application.server.host")
  def port = config.getInt("application.server.port")

  def dbName = config.getString("db.name")
}
