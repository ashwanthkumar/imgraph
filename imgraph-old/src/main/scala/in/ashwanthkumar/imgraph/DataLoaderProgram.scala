package in.ashwanthkumar.imgraph

import akka.actor.{ActorSystem, Props}
import akka.pattern.ask
import akka.util.Timeout
import in.ashwanthkumar.imgraph.datamodel.Vertex

import scala.concurrent.Await
import scala.concurrent.duration.DurationDouble

/*
  Loads a sample Graph to imGraph Machine
 */
object DataLoaderProgram extends App {

  val systemConfig = imGraphConfig.load("imgraph-reference.conf")

  val system = ActorSystem("imGraphSystem")
  val dataManager = system.actorOf(Props(classOf[DataManager], systemConfig.dataManagerConfig.store), name = "dataManager")

  // Push the vertices
  systemConfig.loaderConfig.loader
    .loadVertices
    .foreach(vertex => dataManager ! AddVertex(vertex))

  // Push the edges
  systemConfig.loaderConfig.loader
    .loadEdges
    .foreach(edge => dataManager ! AddEdge(edge))

  implicit val timeout = Timeout(1 second)
  // Lets do some querying on the data
  val vertex = (dataManager ? GetVertex(883301)).mapTo[Option[Vertex]]
  println(Await.result(vertex, 1 second))

  Thread.sleep(10000)
  system.shutdown()
}
