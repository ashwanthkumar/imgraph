package in.ashwanthkumar.imgraph.datamodel

import in.ashwanthkumar.imgraph.types.{ListData, StringData, IntData, Data}

case class Properties(props: Map[String, Data]) {
  def int(name: String) = props(name).asInstanceOf[IntData].data
  def string(name: String) = props(name).asInstanceOf[StringData].data
  def list[L](name: String) = props(name).asInstanceOf[ListData[L]].data

  def contains(key: String) = props.contains(key)
}

object Properties {
  def apply(): Properties = Properties(Map())
}
