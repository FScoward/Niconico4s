package auth

/**
 * Created by FScoward on 15/01/05.
 */

import conf.Configuration
import core.Search
import org.specs2.mutable._
import scala.collection.JavaConversions._

class NicoAuthTest extends Specification {
  "NicoAuth.authenticate" should {
    "get cookie" in {
      val cookie = NicoAuth.authenticate(Configuration.email, Configuration.password)
      cookie must beSome
    }
  }

}
