package auth

import java.io.PrintStream
import java.net.{URL, CookieHandler, CookieManager}
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
      connect.getInputStream
    } catch {
      case NonFatal(e) => System.exit(0)
    }
  }
}
