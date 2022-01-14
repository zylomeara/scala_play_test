package daos

import scala.concurrent.Future

import reactivemongo.api.{AsyncDriver, DB, MongoConnection}

trait ReactiveMongoApi {
  def driver: AsyncDriver
  def connection: Future[MongoConnection]
  def database: Future[DB]
}
