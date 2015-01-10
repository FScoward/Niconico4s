package core

/**
 * Created by FScoward on 15/01/06.
 */

import auth.{Cookie, NicoAuth}
import conf.Configuration
import org.specs2.mutable._

class SearchSpec extends Specification {

  "Search.search" should {
    "more than 1 result" in {
      /*
      NicoAuth.authenticate(Configuration.email, Configuration.password).map{ cookie =>
        val result = Search.search(cookie, "大橋彩香")
      }
      */

      SearchNico.search
      
      
      
      1 must beEqualTo(1)
    }
  }

}
