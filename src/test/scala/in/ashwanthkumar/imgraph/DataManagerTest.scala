package in.ashwanthkumar.imgraph

import akka.actor.{ActorSystem, Props}
import akka.testkit.{ImplicitSender, TestActorRef, TestKit}
import in.ashwanthkumar.imgraph.datamodel.{Edge, EdgeType, Vertex}
import in.ashwanthkumar.imgraph.store.InMemoryStore
import org.scalatest.{BeforeAndAfterAll, FlatSpecLike, Matchers}

import scala.concurrent.duration.DurationDouble

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

}
