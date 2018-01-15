package examples

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

/**
  * This example demonstrates how initiating futures in a for...yield loop can actually lead to
  * inadvertently synchronous execution even though the futures are mapped to run on the global context.
  */
class SequentialOnGlobalWithFor extends FuturesExample {
  override def runExample(): Future[Unit] = {
    for {
      _ <- slowIOBoundTask("A")
      _ <- slowIOBoundTask("B")
      _ <- slowIOBoundTask("C")
      _ <- slowIOBoundTask("D")
      _ <- slowIOBoundTask("E")
    } yield {
      tasksComplete()
    }
  }
}
