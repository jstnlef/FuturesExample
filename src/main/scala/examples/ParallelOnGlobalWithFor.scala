package examples

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

/**
  * This example demonstrates how to use the '=' syntax in the for...yield to start the future execution, and ensure
  * that each of the futures are complete before running `tasksComplete()`
  */
class ParallelOnGlobalWithFor extends FuturesExample {
  override def runExample(): Future[Unit] = {
    println(s"Running example on Thread ${Thread.currentThread.getName}")

    for {
      _ <- Future.unit
      fA = slowIOBoundTask("A")
      fB = slowIOBoundTask("B")
      fC = slowIOBoundTask("C")
      fD = slowIOBoundTask("D")
      fE = slowIOBoundTask("E")
      _ <- fA
      _ <- fB
      _ <- fC
      _ <- fD
      _ <- fE
    } yield {
      tasksComplete()
    }
  }
}
