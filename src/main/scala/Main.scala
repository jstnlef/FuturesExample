import examples._

import scala.concurrent.Await
import scala.concurrent.duration._

object Main {

  val examplesMap: Map[String, FuturesExample] = Map(
    "ParallelOnGlobalWithFor" -> new ParallelOnGlobalWithFor(),
    "SequentialOnGlobalWithFor" -> new SequentialOnGlobalWithFor(),
    "ParallelOnGlobalWithFutureSequence" -> new ParallelOnGlobalWithFutureSequence()
  )

  def main(args: Array[String]): Unit = {
    val thread = Thread.currentThread
    val startTime = System.currentTimeMillis
    if (args.length == 0) {
      println("Need to specify an argument.")
      return
    }

    println(s"Starting main method on Thread ${thread.getName}")
    val example = examplesMap.get(args(0))
    example match {
      case Some(ex) =>
        Await.result(ex.runExample(), 30.seconds)
      case None =>
        println("Invalid example name.")
        return
    }
    val endTime = System.currentTimeMillis
    println(s"Ending main method on Thread ${thread.getName}. Execution took ${endTime - startTime} ms.")
  }
}
