package in.ashwanthkumar.imgraph.types

import in.ashwanthkumar.imgraph.datamodel.{Prop, Properties}

abstract class Data

case class StringData(data: String) extends Data
case class IntData(data: Int) extends Data
case class ListData[T](data: List[T]) extends Data

object DataConversions {
  import scala.language.implicitConversions

  implicit def convertStringToStringData(string: String) = StringData(string)
  implicit def convertIntToIntData(int: Int) = IntData(int)
  implicit def convertListToListData[T](list: List[T]) = ListData(list)

  implicit def stringToStringProp(key: String) = new StringProp(key)
  implicit def convertStringDataTupleToProp(kv: (String, Data)*) = Prop(kv.toMap)

  /*
    TODO: Not sure if this is the right way to go ahead :-/
   */
  implicit def convertPropToProperties(prop1: Prop): Properties = convertPropToProperties(List(prop1))
  implicit def convertPropToProperties(props: (Prop, Prop)): Properties = convertPropToProperties(List(props._1, props._2))
  implicit def convertPropToProperties(props: (Prop, Prop, Prop)): Properties = convertPropToProperties(List(props._1, props._2, props._3))
  implicit def convertPropToProperties(props: (Prop, Prop, Prop, Prop)): Properties = convertPropToProperties(List(props._1, props._2, props._3, props._4))
  implicit def convertPropToProperties(props: (Prop, Prop, Prop, Prop, Prop)): Properties = convertPropToProperties(List(props._1, props._2, props._3, props._4, props._5))
  implicit def convertPropToProperties(props: (Prop, Prop, Prop, Prop, Prop, Prop)): Properties = convertPropToProperties(List(props._1, props._2, props._3, props._4, props._5, props._6))
  implicit def convertPropToProperties(props: (Prop, Prop, Prop, Prop, Prop, Prop, Prop)): Properties = convertPropToProperties(List(props._1, props._2, props._3, props._4, props._5, props._6, props._7))
  implicit def convertPropToProperties(props: (Prop, Prop, Prop, Prop, Prop, Prop, Prop, Prop)): Properties = convertPropToProperties(List(props._1, props._2, props._3, props._4, props._5, props._6, props._7, props._8))
  implicit def convertPropToProperties(props: (Prop, Prop, Prop, Prop, Prop, Prop, Prop, Prop, Prop)): Properties = convertPropToProperties(List(props._1, props._2, props._3, props._4, props._5, props._6, props._7, props._8, props._9))
  implicit def convertPropToProperties(props: (Prop, Prop, Prop, Prop, Prop, Prop, Prop, Prop, Prop, Prop)): Properties = convertPropToProperties(List(props._1, props._2, props._3, props._4, props._5, props._6, props._7, props._8, props._9, props._10))
  implicit def convertPropToProperties(props: (Prop, Prop, Prop, Prop, Prop, Prop, Prop, Prop, Prop, Prop, Prop)): Properties = convertPropToProperties(List(props._1, props._2, props._3, props._4, props._5, props._6, props._7, props._8, props._9, props._10, props._11))
  implicit def convertPropToProperties(props: (Prop, Prop, Prop, Prop, Prop, Prop, Prop, Prop, Prop, Prop, Prop, Prop)): Properties = convertPropToProperties(List(props._1, props._2, props._3, props._4, props._5, props._6, props._7, props._8, props._9, props._10, props._11, props._12))
  implicit def convertPropToProperties(props: (Prop, Prop, Prop, Prop, Prop, Prop, Prop, Prop, Prop, Prop, Prop, Prop, Prop)): Properties = convertPropToProperties(List(props._1, props._2, props._3, props._4, props._5, props._6, props._7, props._8, props._9, props._10, props._11, props._12, props._13))
  implicit def convertPropToProperties(props: (Prop, Prop, Prop, Prop, Prop, Prop, Prop, Prop, Prop, Prop, Prop, Prop, Prop, Prop)): Properties = convertPropToProperties(List(props._1, props._2, props._3, props._4, props._5, props._6, props._7, props._8, props._9, props._10, props._11, props._12, props._13, props._14))
  implicit def convertPropToProperties(props: (Prop, Prop, Prop, Prop, Prop, Prop, Prop, Prop, Prop, Prop, Prop, Prop, Prop, Prop, Prop)): Properties = convertPropToProperties(List(props._1, props._2, props._3, props._4, props._5, props._6, props._7, props._8, props._9, props._10, props._11, props._12, props._13, props._14, props._15))
  implicit def convertPropToProperties(props: (Prop, Prop, Prop, Prop, Prop, Prop, Prop, Prop, Prop, Prop, Prop, Prop, Prop, Prop, Prop, Prop)): Properties = convertPropToProperties(List(props._1, props._2, props._3, props._4, props._5, props._6, props._7, props._8, props._9, props._10, props._11, props._12, props._13, props._14, props._15, props._16))
  implicit def convertPropToProperties(props: (Prop, Prop, Prop, Prop, Prop, Prop, Prop, Prop, Prop, Prop, Prop, Prop, Prop, Prop, Prop, Prop, Prop)): Properties = convertPropToProperties(List(props._1, props._2, props._3, props._4, props._5, props._6, props._7, props._8, props._9, props._10, props._11, props._12, props._13, props._14, props._15, props._16, props._17))
  implicit def convertPropToProperties(props: (Prop, Prop, Prop, Prop, Prop, Prop, Prop, Prop, Prop, Prop, Prop, Prop, Prop, Prop, Prop, Prop, Prop, Prop)): Properties = convertPropToProperties(List(props._1, props._2, props._3, props._4, props._5, props._6, props._7, props._8, props._9, props._10, props._11, props._12, props._13, props._14, props._15, props._16, props._17, props._18))
  implicit def convertPropToProperties(props: (Prop, Prop, Prop, Prop, Prop, Prop, Prop, Prop, Prop, Prop, Prop, Prop, Prop, Prop, Prop, Prop, Prop, Prop, Prop)): Properties = convertPropToProperties(List(props._1, props._2, props._3, props._4, props._5, props._6, props._7, props._8, props._9, props._10, props._11, props._12, props._13, props._14, props._15, props._16, props._17, props._18, props._19))
  implicit def convertPropToProperties(props: (Prop, Prop, Prop, Prop, Prop, Prop, Prop, Prop, Prop, Prop, Prop, Prop, Prop, Prop, Prop, Prop, Prop, Prop, Prop, Prop)): Properties = convertPropToProperties(List(props._1, props._2, props._3, props._4, props._5, props._6, props._7, props._8, props._9, props._10, props._11, props._12, props._13, props._14, props._15, props._16, props._17, props._18, props._19, props._20))
  implicit def convertPropToProperties(props: (Prop, Prop, Prop, Prop, Prop, Prop, Prop, Prop, Prop, Prop, Prop, Prop, Prop, Prop, Prop, Prop, Prop, Prop, Prop, Prop, Prop)): Properties = convertPropToProperties(List(props._1, props._2, props._3, props._4, props._5, props._6, props._7, props._8, props._9, props._10, props._11, props._12, props._13, props._14, props._15, props._16, props._17, props._18, props._19, props._20, props._21))
  implicit def convertPropToProperties(props: (Prop, Prop, Prop, Prop, Prop, Prop, Prop, Prop, Prop, Prop, Prop, Prop, Prop, Prop, Prop, Prop, Prop, Prop, Prop, Prop, Prop, Prop)): Properties = convertPropToProperties(List(props._1, props._2, props._3, props._4, props._5, props._6, props._7, props._8, props._9, props._10, props._11, props._12, props._13, props._14, props._15, props._16, props._17, props._18, props._19, props._20, props._21, props._22))
  implicit def convertPropToProperties(prop: List[Prop]) = {
    val combinedProp = Prop(prop.foldLeft(Map[String, Data]())(_ ++ _.props))
    Properties(props = List(combinedProp))
  }

  class StringProp(key: String) {
    def ~(value: Data) = Prop(key, value)
  }
}
