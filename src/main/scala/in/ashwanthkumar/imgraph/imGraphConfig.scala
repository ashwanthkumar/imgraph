package in.ashwanthkumar.imgraph

import com.typesafe.config.{ConfigFactory, Config}
import in.ashwanthkumar.imgraph.datamodel.Cell
import in.ashwanthkumar.imgraph.loader.DataLoader
import in.ashwanthkumar.imgraph.store.ReadableStore

case class imGraphConfig(dataManagerConfig: DataManagerConfig, loaderConfig: DataLoaderConfig)

object imGraphConfig {

  def load(): imGraphConfig = load("imgraph-reference.conf")

  def load(file: String): imGraphConfig = {
    load(ConfigFactory.load(file))
  }

  def load(config: Config): imGraphConfig = {
    val configToLoad = config
      .withFallback(ConfigFactory.load("my-config-falcon.conf"))
      .withFallback(ConfigFactory.load("imgraph-reference.conf"))

    val dataManagerConfig = toDataManagerConfig(configToLoad.getConfig("imgraph.datamanager"))
    val dataLoaderConfig = toDataLoaderConfig(configToLoad.getConfig("imgraph.dataloader"))
    imGraphConfig(dataManagerConfig, dataLoaderConfig)
  }

  private def toDataLoaderConfig(config: Config) = {
    val strategy = config.getString("loader")
    val networkId = config.getInt("network-id")
    DataLoaderConfig(strategy, networkId)
  }

  private def toDataManagerConfig(config: Config) = {
    DataManagerConfig(config.getString("store"))
  }
}

case class DataManagerConfig(strategy: String) {
  lazy val store = {
    Class
      .forName(strategy)
      .getConstructor()
      .newInstance()
      .asInstanceOf[ReadableStore[Int, Cell]]
  }
}

case class DataLoaderConfig(strategy: String, networkId: Int) {
  lazy val loader = {
    Class
      .forName(strategy)
      .getConstructor(classOf[Integer])
      .newInstance(networkId.asInstanceOf[Integer]).asInstanceOf[DataLoader]
  }
}
