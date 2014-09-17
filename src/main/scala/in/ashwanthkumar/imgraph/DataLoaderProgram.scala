package in.ashwanthkumar.imgraph

import akka.actor.{ActorSystem, Props}

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

  // Print all the edges
  dataManager ! PrintAll


  Thread.sleep(10000)
  system.shutdown()
}
