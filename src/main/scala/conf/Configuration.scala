package conf

import com.typesafe.config.ConfigFactory

/**
 * Created by FScoward on 15/01/05.
 */

object Configuration {
  val (email: String, password: String) = loadProperties

  /**
   * プロパティファイルの読み込み
   * */
  private def loadProperties = {
    // application.conf の読み込み
    val config = ConfigFactory.load()
    (config.getString("email"), config.getString("password"))
  }
}
