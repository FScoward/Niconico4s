package core

import java.io._
import java.net._
import java.util.Date
import java.util.stream.Collectors
import auth.{Cookie, NicoAuth}
import core.input.{Service, Keyword, Query, Join}
import core.output.{LastModified, Result}
import scalaz._, Scalaz._
import argonaut._, Argonaut._

import scala.collection.JavaConversions._

/**
 * Created by FScoward on 15/01/05.
 */
case class DataInfo(id: String,
                    title: String,
                    first_retrieve: String,
                    view_counter: Int,
                    mylist_counter: Int,
                    thumbnail_url: String,
                    num_res: Int,
                    last_res_body: String,
                    length: String,
                    title_short: String,
                    description_short: String,
                    thumbnail_style: Option[String],
                    is_middle_thumbnail: Boolean
                     )

object DataInfo {
  implicit def DataInfoCodecJson: CodecJson[DataInfo] = casecodec13(DataInfo.apply, DataInfo.unapply)("id", "title", "first_retrieve", "view_counter", "mylist_counter", "thumbnail_url",
    "num_res", "last_res_body", "length", "title_short", "description_short", "thumbnail_style", "is_middle_thumbnail")
}

case class DataInfoList(count: Int,
                        page: Int,
                        related_tags: List[String],
                        status: String,
                        has_ng_video_for_adsense_on_listing: Boolean,
                        list: List[DataInfo])

object DataInfoList {
  implicit def DataInfoListCodecJson: CodecJson[DataInfoList] = casecodec6(DataInfoList.apply, DataInfoList.unapply)("count", "page", "related_tags", "status",
    "has_ng_video_for_adsense_on_listing", "list")
}

object SearchNico {

  @deprecated
  def search(cookie: Cookie, word: String): Option[DataInfoList] = {
    val encodedWord = URLEncoder.encode(word, "utf-8")

    val url = s"http://ext.nicovideo.jp/api/search/search/$encodedWord?mode=watch&page=1&sort=f&order=d"

    val connect = new URL(url).openConnection().asInstanceOf[HttpURLConnection]
    connect.setRequestMethod("GET")
    connect.setRequestProperty("Cookie", cookie.userSession)
    val inputStream = connect.getInputStream
    val br = new BufferedReader(new InputStreamReader(inputStream))


    val source = br.lines().collect(Collectors.toList()).mkString.getBytes
    val json = new String(source, "UTF-8")

    Parse.decodeOption[DataInfoList](json)
  }

  /**
   * 検索を行う
   * 
   * @param query 検索クエリ
   * @author FScoward
   * @return {{{Either[IOException, List[Result]]}}}
   * */
  def search(query: Query): Either[IOException, List[Result]] = {
    val endpoint = "http://api.search.nicovideo.jp/api/snapshot/"

    var outputStream: OutputStream = null
    var is: InputStream = null
    var br: BufferedReader = null
    try {
      val connect = new URL(endpoint).openConnection().asInstanceOf[HttpURLConnection]
      // POST可能にする
      connect.setDoOutput(true)
      connect.setRequestProperty("Content-Type", "application/json; charset=utf8")

      outputStream = connect.getOutputStream
      val postData = query.jencode.nospaces

      //    val outputStremWriter = new OutputStreamWriter(outputStream)
      // データをpostする
      outputStream.write(postData.getBytes("UTF-8"))

      // postした結果の取得
      is = connect.getInputStream
      br = new BufferedReader(new InputStreamReader(is, "UTF-8"))

      val lines = Iterator.continually(br.readLine()).takeWhile(_ != null).toList
      
      val resultList: List[Result] = lines.map(_.decodeOption[Result]).flatten

      Right(resultList)
    } catch {
      case e: IOException => Left(e)
    } finally {
      if(null != outputStream) {
        outputStream.close()
      }
      if(null != is) {
        is.close()
      }
      if(null != br) {
        br.close()
      }
    }
  }
  
  /**
   * 切り替え日時の取得を行う
   * 
   * @return {{{Either[IOException, Option[LastModified]]}}}
   * */
  def getLastModified: Either[IOException, Option[LastModified]] = {
    
    // 切り替え日時の取得先
    val url = "http://api.search.nicovideo.jp/api/snapshot/version"
    
    var is: InputStream = null
    var br: BufferedReader = null
    try{
      val connect = new URL(url).openConnection().asInstanceOf[HttpURLConnection]
      is = connect.getInputStream
      br = new BufferedReader(new InputStreamReader(is, "UTF-8"))  
      
      val lines = Iterator.continually(br.readLine()).takeWhile(_ != null).toList
      Right(lines.map(_.decodeOption[LastModified]).head)
    } catch {
      case e: IOException => Left(e)
    } finally {
      if(null != is) {
        is.close()
      }
      if(null != br) {
        br.close()
      }
    }
    
  }
}

