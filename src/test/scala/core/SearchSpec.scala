package core

/**
 * Created by FScoward on 15/01/06.
 */

import auth.{Cookie, NicoAuth}
import conf.Configuration
import core.input._
import org.specs2.mutable._

class SearchSpec extends Specification {

  "Search.search" should {
    "more than 1 result" in {
      /*
      NicoAuth.authenticate(Configuration.email, Configuration.password).map{ cookie =>
        val result = Search.search(cookie, "大橋彩香")
      }
      */
      val query = Query("高垣彩陽", List(Service.video), Keyword(), List(Join.cmsid, Join.title, Join.view_counter), None, Some(SortBy.start_time), None, None, None, "NicoNico4s")
      SearchNico.search(query) must beRight
    }
  }

}
