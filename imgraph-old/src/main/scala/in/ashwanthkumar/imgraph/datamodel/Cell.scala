package in.ashwanthkumar.imgraph.datamodel

import in.ashwanthkumar.imgraph.datamodel.Constants._
import in.ashwanthkumar.imgraph.store.ReadableStore
import in.ashwanthkumar.imgraph.types.Data
import in.ashwanthkumar.imgraph.types.DataConversions._

trait Cell {
  require(hasRequiredProperties, s"Cell-$id doesn't have all these ${requiredProperties.mkString(",")} properties")
  val id: Int
  val properties: Prop

  protected def requiredProperties: List[String]
  protected def hasRequiredProperties: Boolean = requiredProperties.forall(properties.contains)
}

case class Edge(id: Int, properties: Prop) extends Cell {

  def left: Int = properties.int(LEFT_VERTEX)
  def right: Int = properties.int(RIGHT_VERTEX)
  def `type`: String = properties.string(EDGE_TYPE)
  def name = properties.string(LABEL)

  protected def requiredProperties = List(LEFT_VERTEX, RIGHT_VERTEX, EDGE_TYPE, LABEL)
}

object Edge {
  def apply(id: Int, leftVertexId: Int, rightVertexId: Int, label: String, edgeType: String): Edge = {
    val props = Prop(Map(
      LABEL -> label,
      LEFT_VERTEX -> leftVertexId,
      RIGHT_VERTEX -> rightVertexId,
      EDGE_TYPE -> edgeType
    ))

    Edge(id, props)
  }

  def apply(leftVertexId: Int, rightVertexId: Int, label: String, edgeType: String): Edge = {
    apply(-1, leftVertexId, rightVertexId, label, edgeType)
  }
}
object EdgeType {
  val IN = "IN"
  val OUT = "OT"
//  val UNDIRECTED = "UD"
}

case class Vertex(id: Int, properties: Prop) extends Cell {
  def edges = properties.list[Int](VERTEX_EDGES)
  /*
    edges(ReadableStore) would give you resolved Edge objects, while
    edges would return just edgeIds
   */
  def edges(store: ReadableStore[Int, Cell]): Iterable[Option[Edge]] = {
    edges
      .map(id =>store.get(id).asInstanceOf[Option[Edge]])
      .filter(_.isDefined)
  }
  def name = properties.string(LABEL)
  def addEdge(id: Int) = {
    this.copy(properties = properties.put(VERTEX_EDGES, edges ++ List(id)))
  }

  protected def requiredProperties = List(VERTEX_EDGES, LABEL)
}

object Vertex {
  def apply(id: Int, label: String, edges: List[Int]): Vertex = {
    val props = Prop(Map(
      LABEL -> label,
      VERTEX_EDGES -> edges
    ))

    Vertex(id, props)
  }

  def apply(label: String, knownProps: Prop): Vertex = {
    val labelMap: Map[String, Data] = Map(LABEL -> label, VERTEX_EDGES -> List())
    val newProps = Prop(labelMap ++ knownProps.props)
    Vertex(-1, newProps)
  }
}
