package daos

import scala.concurrent.{ExecutionContext, Future}
import reactivemongo.api.{AsyncDriver, DB, MongoConnection}
import reactivemongo.api.MongoConnection.ParsedURIWithDB

import javax.inject.{Inject, Singleton}

@Singleton
class ReactiveMongoApiImpl @Inject()(implicit ec: ExecutionContext) extends ReactiveMongoApi {
  override val driver: AsyncDriver = AsyncDriver()
  val parsedUri: Future[ParsedURIWithDB] = MongoConnection.fromStringWithDB("mongodb://localhost:27017/todos")
  override val connection: Future[MongoConnection] = parsedUri
    .flatMap(parsed => driver.connect(parsed, Some("ConnectionName"), false))

  override def database: Future[DB] = parsedUri.flatMap(uri => connection.flatMap(_.database(uri.db)))
}
