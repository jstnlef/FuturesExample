package examples

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

/**
  * Example here is equivalent to the ParallelOnGlobalWithFor example
  */
class ParallelOnGlobalWithFutureSequence extends FuturesExample {
  override def runExample(): Future[Unit] = {
    println(s"Running example on Thread ${Thread.currentThread.getName}")

    // Starts the various task futures
    val slowTasks = Seq(
      slowIOBoundTask("A"),
      slowIOBoundTask("B"),
      slowIOBoundTask("C"),
      slowIOBoundTask("D"),
      slowIOBoundTask("E")
    )
    Future.sequence(slowTasks).map(_ => tasksComplete())
  }
}
