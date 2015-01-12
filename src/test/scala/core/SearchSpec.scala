package core

/**
 * Created by FScoward on 15/01/06.
 */

import auth.{Cookie, NicoAuth}
import conf.Configuration
import core.input._
import core.output.{Value, LastModified}
import org.specs2._
import org.specs2.specification._

class SearchSpec extends Specification {
  
  def is =
    s2"""
         getLastModified $e1
         search          $e2
      """

  def e1 = {
    SearchNico.getLastModified must beRight[Option[LastModified]]
  }
  
  def e2 = {
    val query = Query("高垣彩陽", List(Service.video), Keyword(), List(Join.cmsid, Join.title, Join.view_counter), None, Some(SortBy.start_time), None, None, None, "NicoNico4s")
    SearchNico.search(query) must beRight[Option[List[Value]]]
  }
  
  /*
  "Search.search" should {
    "more than 1 result" in {
      SearchNico.getLastModified
      /*
      NicoAuth.authenticate(Configuration.email, Configuration.password).map{ cookie =>
        val result = Search.search(cookie, "大橋彩香")
      }
      */
      val query = Query("高垣彩陽", List(Service.video), Keyword(), List(Join.cmsid, Join.title, Join.view_counter), None, Some(SortBy.start_time), None, None, None, "NicoNico4s")
      SearchNico.search(query) must beRight
    }
  }
  */
}
