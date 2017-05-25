package in.ashwanthkumar.imgraph.loader

import java.io.File

import in.ashwanthkumar.imgraph.datamodel.{Edge, EdgeType, Prop, Vertex}
import in.ashwanthkumar.imgraph.types.{Data, IntData}

import scala.io.Source

class LoadEgoNetwork(egoNetworkId: Integer) extends DataLoader {
  private val basePath = s"/snap_twitter/$egoNetworkId/"

  protected lazy val featureMap = {
    readFile(s"$egoNetworkId.featnames")
      .map(line => line.split(" ", 2))
      .map(splits => splits(0).toInt -> splits(1))
      .toMap
  }

  override def loadVertices: Iterable[Vertex] = (verticesInNetwork ++ Iterator(mainVertex)).toIterable

  override def loadEdges: Iterable[Edge] = readEdgesFromFile.toIterable

  private[loader] def createVertex(vertexId: Int, label: String, vertexFeatures: String) = {
    Vertex(label, parseVertexFeatures(vertexFeatures)).copy(id = vertexId)
  }

  private[loader] def parseVertexFeatures(featureString: String): Prop = {
    val propMap: Map[String, Data] = featureString.split(" ").toList.zipWithIndex
      .filter(_._1 == "1")
      .map(_._2)
      .map(index => featureMap(index) -> IntData(1))
      .toMap

    Prop(propMap.toMap)
  }

  private[loader] def createEdge(vertexTuples: (Int, Int), edgeLabel: String, edgeType: String) = {
    val (leftVertexId, rightVertexId) = vertexTuples
    Edge(leftVertexId.toInt, rightVertexId.toInt, edgeLabel, edgeType)
  }

  private[loader] def readFile(fileName: String) = {
    Source.fromInputStream(getClass.getResourceAsStream(s"$basePath/$fileName"))
      .getLines()
  }

  private[loader] def mainVertex = {
    createVertex(egoNetworkId, egoNetworkId.toString, readFile(s"$egoNetworkId.egofeat").mkString)
  }

  private[loader] def verticesInNetwork = {
    readFile(s"$egoNetworkId.feat")
      .map(line => line.split(" ", 2))
      .map(tokens => tokens(0) -> tokens(1))
      .map { case (label, vertexFeatures) => createVertex(label.toInt, label, vertexFeatures)}
      .toIterator
  }

  private[loader] def readEdgesFromFile = {
    readFile(s"$egoNetworkId.edges")
      .map(line => line.split(" ", 2))
      .map(tokens => tokens(0).toInt -> tokens(1).toInt)
      .map(vertexIdTuples => createEdge(vertexIdTuples, "follows", EdgeType.OUT))
      .toIterator
  }

}
