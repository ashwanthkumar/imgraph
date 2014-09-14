package in.ashwanthkumar.imgraph.query

import in.ashwanthkumar.imgraph.datamodel.{Constants, EdgeType, Prop}
import in.ashwanthkumar.imgraph.types.DataConversions._
import org.scalatest.{FlatSpec, Matchers}
import Query._

class QueryTest extends FlatSpec with Matchers {
  def defaultQuery = Query(Prop.MATCH_ALL, List("*"), EdgeType.IN, Prop.MATCH_ALL)

  "Query" should "check for sensible defaults" in {
    Query() should be(defaultQuery)
  }

  it should "set the edgeType as IN for <--" in {
    Query() <-- Prop.MATCH_ALL should be(defaultQuery.copy(edgeType = EdgeType.IN))
  }

  it should "set the edgeType as OUT for -->" in {
    Query() --> Prop.MATCH_ALL should be(defaultQuery.copy(edgeType = EdgeType.OUT))
  }

  it should "set matchProperties on MATCH" in {
    Query() MATCH (("name" ~ "Romeo") | ("age" ~ 22)) should be(Query(matchProperties = ("name" ~ "Romeo") | ("age" ~ 22)))
  }

  it should "set propsToReturn on RETURN" in {
    Query() RETURN Constants.LABEL should be(defaultQuery.copy(propsToReturn = List(Constants.LABEL)))
  }

  it should "set right properties in Query" in {
    val romeoLovesWho = Query()
      .MATCH(("name" ~ "Romeo") | ("age" ~ 22)) --> LABEL("loves") RETURN ("name", "age")

    romeoLovesWho should be(Query(matchProperties = ("name" ~ "Romeo") | ("age" ~ 22), propsToReturn = List("name", "age"), edgeType = EdgeType.OUT, edgeProps = LABEL("loves")))
  }

}
