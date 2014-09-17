package in.ashwanthkumar.imgraph.datamodel

import in.ashwanthkumar.imgraph.types.DataConversions._
import in.ashwanthkumar.imgraph.types.{Data, IntData, ListData, StringData}

case class Prop(props: Map[String, Data]) {
  def int(name: String) = props(name).asInstanceOf[IntData].data
  def string(name: String) = props(name).asInstanceOf[StringData].data
  def list[L](name: String) = props(name).asInstanceOf[ListData[L]].data

  def contains(key: String) = props.contains(key)
  def get(key: String) = props.get(key)
  def put(key: String, value: Data) = this.copy(props = props + (key -> value))
  def matches(anotherProperty: Prop) = {
    anotherProperty.props
      .forall(t => isWildCardMatch(t._1) || get(t._1) == Some(t._2))
  }

  private def isWildCardMatch(key: String) = key == "*"
}

object Prop {
  def apply(key: String, value: Data): Prop = Prop(Map(key -> value))
  // Matches all nodes
  def MATCH_ALL = Prop("*" , "*")
}

case class Properties(props: List[Prop] = List()) {
  def |(anotherProp: Prop) = this.copy(props = anotherProp :: props)
  def |(anotherProperties: Properties) = this.copy(props = anotherProperties.props ++ props)
}

