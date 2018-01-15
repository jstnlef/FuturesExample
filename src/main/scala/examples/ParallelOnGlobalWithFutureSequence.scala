package examples

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

/**
  * Example here is equivalent to the ParallelOnGlobalWithFor example
  */
class ParallelOnGlobalWithFutureSequence extends FuturesExample {
  override def runExample(): Future[Unit] = {
    Future.sequence(Seq(
      slowIOBoundTask("A"),
      slowIOBoundTask("B"),
      slowIOBoundTask("C"),
      slowIOBoundTask("D"),
      slowIOBoundTask("E")
    )).map(_ => tasksComplete())
  }
}
