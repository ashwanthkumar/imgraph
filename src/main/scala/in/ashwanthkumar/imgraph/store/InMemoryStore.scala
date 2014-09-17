package in.ashwanthkumar.imgraph.store

import in.ashwanthkumar.imgraph.datamodel.Cell

class InMemoryStore(var store: Map[Int, Cell] = Map()) extends Store[Int, Cell] {

  override def get(key: Int): Option[Cell] = store.get(key)

  override def put(key: Int, value: Cell): Unit = {
    store += (key -> value)
  }

  override def iterate: Iterator[(Int, Cell)] = store.iterator
}

object InMemoryStore {
  def fromMap(kvs: Map[Int, Cell]) = new InMemoryStore(kvs)

}
