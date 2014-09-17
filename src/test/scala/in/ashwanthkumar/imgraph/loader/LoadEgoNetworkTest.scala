package in.ashwanthkumar.imgraph.loader

import in.ashwanthkumar.imgraph.datamodel.Prop
import org.scalatest.{Matchers, FlatSpec}
import in.ashwanthkumar.imgraph.types.DataConversions._

class LoadEgoNetworkTest extends FlatSpec with Matchers {

  "LoadEgoNetwork" should "parseVertexFeatures correctly" in {
    val egoNetworkLoader = new LoadEgoNetwork(1) {
      override protected lazy val featureMap: Map[Int, String] = Map(0 -> "f0", 1 -> "f1", 2 -> "f2", 3 -> "f3")
    }
    egoNetworkLoader.parseVertexFeatures("0 1 1 0") should be(Prop(Map("f1" -> 1, "f2" -> 1)))
    egoNetworkLoader.parseVertexFeatures("1 1 1 1") should be(Prop(Map("f0" -> 1, "f1" -> 1, "f2" -> 1, "f3" -> 1)))
    egoNetworkLoader.parseVertexFeatures("0 0 0 0") should be(Prop(Map()))
  }

}
