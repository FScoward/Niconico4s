package auth

/**
 * Created by FScoward on 15/01/05.
 */

import org.specs2.mutable._
import scala.collection.JavaConversions._

class NicoAuthTest extends Specification {
  "NicoAuth.authenticate" should {
    "get cookie" in {
        NicoAuth.cookieManager.getCookieStore.getCookies.size() must_== 0
        NicoAuth.authenticate("email", "password")
        NicoAuth.cookieManager.getCookieStore.getCookies.size() must_== 1
    }
  }
}
