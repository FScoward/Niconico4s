package auth

/**
 * Created by FScoward on 15/01/05.
 */

import conf.Configuration
import org.specs2.mutable._
import scala.collection.JavaConversions._

class NicoAuthTest extends Specification {
  "NicoAuth.authenticate" should {
    "get cookie" in {
      NicoAuth.cookieManager.getCookieStore.getCookies.size() must_== 0
      NicoAuth.authenticate(Configuration.email, Configuration.password)
      NicoAuth.cookieManager.getCookieStore.getCookies.size() must_== 1
    }
  }

}
