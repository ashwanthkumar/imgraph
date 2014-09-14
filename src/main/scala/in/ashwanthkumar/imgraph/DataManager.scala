package in.ashwanthkumar.imgraph

import akka.actor.{Actor, ActorLogging}
import in.ashwanthkumar.imgraph.datamodel.{Cell, Edge, Vertex}
import in.ashwanthkumar.imgraph.store.ReadableStore

import scala.util.Try

/*
  Process that runs on all machines that hosts the graph data.
  Initial set of responsibilities
    - Provides wrapper access to ReadableStore implementation
    - Responsible for computations on the graph
      - Aggregations
      - Traversals
      - GET queries
      - Property search
 */
class DataManager(store: ReadableStore[Int, Cell]) extends Actor with ActorLogging {

  def edge(id: Int) = store.get(id).flatMap(c => Try(c.asInstanceOf[Edge]).toOption)
  def vertex(id: Int) = store.get(id).flatMap(c => Try(c.asInstanceOf[Vertex]).toOption)

  override def receive: Receive = {
    case GetEdge(id) => sender() ! edge(id)
    case GetVertex(id) => sender() ! vertex(id)
  }
}
