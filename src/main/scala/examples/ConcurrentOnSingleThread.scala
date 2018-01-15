package examples

import java.util.concurrent.{ExecutorService, Executors}

import scala.concurrent.{ExecutionContext, Future}

/**
  * Example here is equivalent to the ParallelOnGlobalWithFutureSequence example run with a single thread
  */
class ConcurrentOnSingleThread extends FuturesExample {
  // Creates a thread pool with a single thread.
  val singleThreadedPool: ExecutorService = Executors.newFixedThreadPool(1)

  // Creates an ExecutionContext with which to run any futures in scope on.
  implicit val ec: ExecutionContext = ExecutionContext.fromExecutorService(singleThreadedPool)

  /**
    * This method will execute on the main thread, post 5 slowIOBoundTasks to the singleThreadedPool and print the
    * tasksComplete message when all of the futures have completed.
    */
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
