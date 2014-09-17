package in.ashwanthkumar.imgraph.loader

import in.ashwanthkumar.imgraph.datamodel.{Edge, Vertex}

trait DataLoader {
  def loadVertices: Iterable[Vertex]

  def loadEdges: Iterable[Edge]
}
