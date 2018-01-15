package examples

import scala.concurrent.{ExecutionContext, Future}

trait FuturesExample {
  def runExample(): Future[Unit]

  def slowIOBoundTask(name: String)(implicit ec: ExecutionContext): Future[Unit] = Future {
    val thread = Thread.currentThread
    println(s"Starting IO task $name on Thread ${thread.getName}")
    Thread.sleep(1000)
    println(s"Ending IO task $name on Thread ${thread.getName}")
  }

  def tasksComplete(): Unit = {
    val thread = Thread.currentThread
    println(s"All Tasks Complete on Thread ${thread.getName}")
  }
}
