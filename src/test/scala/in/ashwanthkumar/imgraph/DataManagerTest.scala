package in.ashwanthkumar.imgraph

import akka.actor.{ActorSystem, Props}
import akka.testkit.{ImplicitSender, TestActorRef, TestKit}
import in.ashwanthkumar.imgraph.datamodel.{Edge, EdgeType, Vertex}
import in.ashwanthkumar.imgraph.store.InMemoryStore
import org.scalatest.{BeforeAndAfterAll, FlatSpecLike, Matchers}

import scala.concurrent.duration.DurationDouble
import scala.util.Random

class DataManagerTest(_system: ActorSystem)
  extends TestKit(_system)
  with ImplicitSender
  with FlatSpecLike
  with Matchers
  with BeforeAndAfterAll {

  def this() = this(ActorSystem("DataManagerSpec"))

  override def afterAll(): Unit = {
    _system.shutdown()
    _system.awaitTermination(10 seconds)
  }

  val store = {
    def testData = Map(
      1 -> Vertex(1, "Romeo", List(3, 4)),
      2 -> Vertex(2, "Juliet", List(3)),
      3 -> Edge(3, 1, 2, "Loves", EdgeType.IN),
      4 -> Edge(4, 2, 1, "Hates", EdgeType.OUT)
    )

    InMemoryStore.fromMap(testData)
  }

  it should "get an edge via message" in {
    val dataManager = TestActorRef(Props(classOf[DataManager], store))
    dataManager ! GetEdge(1)
    expectMsgType[Option[Edge]] should be(None)

    dataManager ! GetEdge(3)
    expectMsgType[Option[Edge]] should be(Some(Edge(3, 1, 2, "Loves", EdgeType.IN)))
  }

  it should "get Vertex via message" in {
    val dataManager = TestActorRef(Props(classOf[DataManager], store))
    dataManager ! GetVertex(3)
    expectMsgType[Option[Vertex]] should be(None)

    dataManager ! GetVertex(1)
    expectMsgType[Option[Vertex]] should be(Some(Vertex(1, "Romeo", List(3, 4))))
  }

  it should "add given vertex via message" in {
    val dataManager = TestActorRef(Props(classOf[DataManager], new InMemoryStore()))
    val vertexToAdd = Vertex(id = -1, "Romeo", List())
    dataManager ! AddVertex(vertexToAdd)

    dataManager ! GetVertex(1)
    expectMsgType[Option[Vertex]] should be(Some(vertexToAdd.copy(id = 1)))
  }

  it should "add a new edge via AddEdge message" in {
    val dataManager = TestActorRef(Props(classOf[DataManager], new InMemoryStore()))
    val v1 = Vertex(id = -1, "Romeo", List())
    val v2 = Vertex(id = -1, "Juliet", List())
    dataManager ! AddVertex(v1)
    dataManager ! AddVertex(v2)

    val edgeToAdd = Edge(id = -1, 1, 2, "loves", EdgeType.IN)
    dataManager ! AddEdge(edgeToAdd)

    dataManager ! GetEdge(3)
    expectMsgType[Option[Edge]] should be(Some(edgeToAdd.copy(id = 3)))
  }

  it should "update the edges for a vertex via AddEdgeToVertex message" in {
    val dataManager = TestActorRef(Props(classOf[DataManager], new InMemoryStore()))
    val v1 = Vertex(id = -1, "Romeo", List())
    dataManager ! AddVertex(v1)
    dataManager ! AddEdgeToVertex(1, 2)

    dataManager ! GetVertex(1)
    expectMsgType[Option[Vertex]].map(_.edges) should be(Some(List(2)))
  }

}
