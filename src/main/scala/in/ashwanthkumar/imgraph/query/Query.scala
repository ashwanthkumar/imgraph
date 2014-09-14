package in.ashwanthkumar.imgraph.query

import in.ashwanthkumar.imgraph.datamodel.Prop._
import in.ashwanthkumar.imgraph.datamodel.{Constants, Prop, EdgeType, Properties}
import in.ashwanthkumar.imgraph.types.Data
import in.ashwanthkumar.imgraph.types.DataConversions._

/*
  DSL for querying Graph data, inspired from Neo4J's Cypher(http://docs.neo4j.org/chunked/stable/cypher-query-lang.html)

  eg.
    To find all lovers of Romeo
    `
      Query()
        .MATCH(("name" ~ "Romeo", "age" ~ 22)) --> LABEL("loves") RETURN ("name", "age")
    `

    Above query would
      1. find the node that has "name"="Romeo" and "age" = 22
      2. find the outgoing edges that says "loves"
      3. return the "name" and "age" props of the resulting nodes


    `
      Query()
        .MATCH(("name" ~ "Romeo") | ("age" ~ 22)) <-- LABEL("loves") RETURN("name", "age")
    `
    Similarly to find who all loves Romeo
 */
case class Query(matchProperties: Properties = MATCH_ALL, propsToReturn: List[String] = List("*"),
                 edgeType: String = EdgeType.IN, edgeProps: Properties = MATCH_ALL) {

  def MATCH(matchProperties: Properties) = this.copy(matchProperties = matchProperties)

  def <--(edgePropsToMatch: Properties) = {
    this.copy(edgeType = EdgeType.IN, edgeProps = edgePropsToMatch)
  }

  def -->(edgePropsToMatch: Properties) = {
    this.copy(edgeType = EdgeType.OUT, edgeProps = edgePropsToMatch)
  }

  def RETURN(propsToReturn: List[String]) = this.copy(propsToReturn = propsToReturn)
  def RETURN(props: String*): Query = RETURN(props.toList)
}


object Query {
  def LABEL(label: String): Properties = Prop(Constants.LABEL, label)
}
