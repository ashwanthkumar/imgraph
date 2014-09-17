package in.ashwanthkumar.imgraph

import in.ashwanthkumar.imgraph.datamodel.{Edge, Vertex}

case class GetEdge(edgeId: Int)
case class GetVertex(vertexId: Int)
case class AddVertex(vertex: Vertex)
case class AddEdge(edge: Edge)
case class AddEdgeToVertex(vertexId: Int, newEdgeId: Int)
case object PrintAll
