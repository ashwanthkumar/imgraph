package in.ashwanthkumar.imgraph.store

trait ReadableStore[K, V] {
  def get(key: K): Option[V]
}
trait WritableStore[K, V] {
  def put(key: K, value: V): Unit
}

trait Store[K, V] extends ReadableStore[K, V] with WritableStore[K, V] {
  def iterate: Iterator[(K, V)]
}