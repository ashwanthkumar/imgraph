package in.ashwanthkumar.imgraph.datamodel

import in.ashwanthkumar.imgraph.datamodel.Constants._
import in.ashwanthkumar.imgraph.types.{IntData, StringData}

trait Cell {
  val id: Int
  val properties: Properties
}

case class Edge(id: Int, properties: Properties) extends Cell {
  require(hasRequiredProperties, s"Edge-$id doesn't have all these ${requiredProperties.mkString(",")} properties")

  def left: Int = properties.int(LEFT_VERTEX)
  def right: Int = properties.int(RIGHT_VERTEX)
  def `type`: String = properties.string(EDGE_TYPE)
  def name = properties.string(LABEL)

  private def requiredProperties = List(LEFT_VERTEX, RIGHT_VERTEX, EDGE_TYPE)
  private def hasRequiredProperties: Boolean = requiredProperties.forall(properties.contains)
}

object Edge {
  import in.ashwanthkumar.imgraph.types.DataConversions._

  def apply(id: Int, leftVertexId: Int, rightVertexId: Int, label: String, edgeType: String): Edge = {
    val props = Properties(Map(
      LABEL -> label,
      LEFT_VERTEX -> leftVertexId,
      RIGHT_VERTEX -> rightVertexId,
      EDGE_TYPE -> edgeType
    ))

    Edge(id, props)
  }
}

case class Vertex(id: Int, properties: Properties) extends Cell {
  def edges = properties.list[Int](VERTEX_EDGES)
}
