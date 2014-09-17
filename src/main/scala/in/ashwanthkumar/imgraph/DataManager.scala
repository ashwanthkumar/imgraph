package in.ashwanthkumar.imgraph

import akka.actor.{Actor, ActorLogging}
import in.ashwanthkumar.imgraph.datamodel.{Cell, Edge, Vertex}
import in.ashwanthkumar.imgraph.store.Store

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
class DataManager(store: Store[Int, Cell]) extends Actor with ActorLogging with IdService {

  def edge(id: Int) = store.get(id).flatMap(c => Try(c.asInstanceOf[Edge]).toOption)
  def vertex(id: Int) = store.get(id).flatMap(c => Try(c.asInstanceOf[Vertex]).toOption)

  override def receive: Receive = {
    case GetEdge(id) => sender() ! edge(id)
    case GetVertex(id) => sender() ! vertex(id)
    case AddVertex(vertex) =>
      val vertexId = if(vertex.id == -1) nextId else vertex.id
      store.put(vertexId, vertex.copy(id = vertexId))
    case AddEdgeToVertex(vertexId, newEdgeId) =>
      vertex(vertexId)
        .map(_.addEdge(newEdgeId))
        .foreach(updatedVertex => store.put(updatedVertex.id, updatedVertex))
    case AddEdge(edge) =>
      val edgeId = if(edge.id == -1) nextId else edge.id
      store.put(edgeId, edge.copy(id = edgeId))

      self ! AddEdgeToVertex(edge.left, edgeId)
      self ! AddEdgeToVertex(edge.right, edgeId)

    case PrintAll =>
      /* For debugging purposes */
      store.iterate.foreach(println)
  }
}

trait IdService {
  private var currentId = 0

  def nextId = {
    currentId += 1
    currentId
  }
}