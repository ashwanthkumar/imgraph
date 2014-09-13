package in.ashwanthkumar.imgraph.types

abstract class Data

case class StringData(data: String) extends Data
case class IntData(data: Int) extends Data
case class ListData[T](data: List[T]) extends Data

object DataConversions {
  import scala.language.implicitConversions

  implicit def convertStringToStringData(string: String) = StringData(string)
  implicit def convertIntToIntData(int: Int) = IntData(int)
  implicit def convertListToListData[T](list: List[T]) = ListData(list)
}
