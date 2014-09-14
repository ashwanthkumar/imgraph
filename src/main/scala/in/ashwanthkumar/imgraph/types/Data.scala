package in.ashwanthkumar.imgraph.types

import in.ashwanthkumar.imgraph.datamodel.{Properties, Prop}

abstract class Data

case class StringData(data: String) extends Data
case class IntData(data: Int) extends Data
case class ListData[T](data: List[T]) extends Data

object DataConversions {
  import scala.language.implicitConversions

  implicit def convertStringToStringData(string: String) = StringData(string)
  implicit def convertIntToIntData(int: Int) = IntData(int)
  implicit def convertListToListData[T](list: List[T]) = ListData(list)
  implicit def convertPropToProperties(prop: Prop) = Properties(props = List(prop))

  implicit def convertTupleToProperties(kv: (String, Data)) = convertPropToProperties(Prop(kv._1, kv._2))
  implicit def convertString2StringTupleToProperties(kv: (String, String)) = convertPropToProperties(Prop(kv._1, kv._2))
  implicit def convertString2IntTupleToProperties(kv: (String, Int)) = convertPropToProperties(Prop(kv._1, kv._2))
  implicit def stringToStringProp(key: String) = new StringProp(key)

  class StringProp(key: String) {
    def ~(value: Data): Properties = Prop(key, value)
  }
}
