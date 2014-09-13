package in.ashwanthkumar.imgraph.store

import in.ashwanthkumar.imgraph.datamodel.Cell

class InMemoryStore(var store: Map[Int, Cell] = Map()) extends ReadableStore[Int, Cell] with WritableStore[Int, Cell] {

  override def get(key: Int): Option[Cell] = store.get(key)

  override def put(key: Int, value: Cell): Unit = {
    store += (key -> value)
  }
}

object InMemoryStore {
  def fromMap(kvs: Map[Int, Cell]) = new InMemoryStore(kvs)
}
