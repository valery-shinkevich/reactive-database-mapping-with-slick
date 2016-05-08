package org.kunicki.conference

import org.kunicki.conference.db.{DatabaseSchema, InitialData}
import slick.driver.H2Driver.api._

import scala.concurrent.Await
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.Duration

object Main extends App with DatabaseSchema with InitialData with Magic {

  val db = Database.forConfig("h2")

  private val future = createSchemaIfNotExists.flatMap(_ => insertInitialData())
  Await.ready(future, Duration.Inf)
}
