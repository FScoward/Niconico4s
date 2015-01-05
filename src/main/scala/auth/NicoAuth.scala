package auth

import java.io.{InputStreamReader, BufferedReader, PrintStream}
import java.net.{URL, CookieHandler, CookieManager}
import java.util
import java.util.stream.Collectors
import scala.collection.JavaConversions._
import scala.util.control.NonFatal

/**
 * Created by FScoward on 15/01/05.
 */
object NicoAuth {
  val cookieManager = new CookieManager()

  def authenticate(mail: String, password: String) = {
    // Cookieを使用可能にする
    CookieHandler.setDefault(cookieManager)

    val loginUrl = "https://secure.nicovideo.jp/secure/login?site=niconico"

//    var bufferedReader: BufferedReader = null
    try{
      val connect = new URL(loginUrl).openConnection()
      // POST可能にする
      connect.setDoOutput(true)

      val outputStream = connect.getOutputStream
      val postData = s"mail=$mail&password=$password"
      val printStream = new PrintStream(outputStream)

      // データをpostする
      printStream.print(postData)
      printStream.close()

      // postした結果の取得
      val inputStream = connect.getInputStream
//      bufferedReader = new BufferedReader(new InputStreamReader(inputStream))
    } catch {
      case NonFatal(e) => System.exit(0)
    } finally {
//      bufferedReader.close()
    }

//    cookieManager.getCookieStore.getCookies.foreach(println)

  }
}
