package in.ashwanthkumar.imgraph.store

import in.ashwanthkumar.imgraph.datamodel.{Edge, EdgeType, Vertex}
import org.scalatest.{FlatSpec, Matchers}

class InMemoryStoreTest extends FlatSpec with Matchers {
  "InMemoryStore" should "be able to get values from existing key values" in {
    val store = InMemoryStore.fromMap(Map(
      1 -> Vertex(1, "V1", List(3)),
      2 -> Vertex(2, "V2", List(3)),
      3 -> Edge(3, 1, 2, "E1", EdgeType.UNDIRECTED)
    ))

    store.get(1) should be(Some(Vertex(1, "V1", List(3))))
    store.get(2) should be(Some(Vertex(2, "V2", List(3))))
    store.get(3) should be(Some(Edge(3, 1, 2, "E1", EdgeType.UNDIRECTED)))
    store.get(4) should be(None)
  }

  it should "be able to add new kvs" in {
    val store = new InMemoryStore()
    store.put(1, Vertex(1, "V1", List(3)))
    store.put(2, Vertex(2, "V2", List(3)))
    store.put(3, Edge(3, 1, 2, "E1", EdgeType.UNDIRECTED))

    store.get(1) should be(Some(Vertex(1, "V1", List(3))))
    store.get(2) should be(Some(Vertex(2, "V2", List(3))))
    store.get(3) should be(Some(Edge(3, 1, 2, "E1", EdgeType.UNDIRECTED)))
    store.get(4) should be(None)
  }
}
