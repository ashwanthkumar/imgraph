package in.ashwanthkumar.imgraph.store

trait ReadableStore[K, V] {
  def get(key: K): Option[V]
}
trait WritableStore[K, V] {
  def put(key: K, value: V): Unit
}