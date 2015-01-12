package core

import java.io._
import java.net._
import java.util.stream.Collectors
import auth.{Cookie, NicoAuth}
import core.input.{Service, Keyword, Query, Join}
import core.output.Result
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
    println(encodedWord)

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

  def search(query: Query): Option[List[output.Value]] = {
    val endpoint = "http://api.search.nicovideo.jp/api/snapshot/"

    val connect = new URL(endpoint).openConnection().asInstanceOf[HttpURLConnection]
    // POST可能にする
    connect.setDoOutput(true)
    connect.setRequestProperty("Content-Type", "application/json; charset=utf8")

    val outputStream = connect.getOutputStream
    val postData = query.jencode.nospaces

    //    val outputStremWriter = new OutputStreamWriter(outputStream)
    // データをpostする
    outputStream.write(postData.getBytes("UTF-8"))
    outputStream.close()

    // postした結果の取得
    val is: InputStream = connect.getInputStream
    val br = new BufferedReader(new InputStreamReader(is, "UTF-8"))

    val result = br.lines().collect(Collectors.toList()).mkString("\n").split("\n")

    val encodedResult = result(0).decodeOption[Result]
    encodedResult.flatMap(result => result.values)
  }
}

