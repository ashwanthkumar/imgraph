package in.ashwanthkumar.imgraph.datamodel

import in.ashwanthkumar.imgraph.datamodel.Constants._
import in.ashwanthkumar.imgraph.types.{IntData, StringData}
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
}
object EdgeType {
  val IN = "IN"
  val OUT = "OT"
//  val UNDIRECTED = "UD"
}

case class Vertex(id: Int, properties: Prop) extends Cell {
  def edges = properties.list[Int](VERTEX_EDGES)
  def name = properties.string(LABEL)

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
}
