package core.input

import argonaut.Argonaut._
import argonaut.EncodeJson

/**
 * Created by FScoward on 2015/01/11.
 */

case class Filter(`type`: String,
                  field: String,
                  value: Option[String] = None,
                  from: Option[String] = None,
                  to: Option[String] = None,
                  include_lower: Option[Boolean] = None,
                  include_upper: Option[Boolean] = None)
object Filter {
  implicit def FilterEncodeJson: EncodeJson[Filter] =
    jencode7L(Function.unlift(unapply))(
      "type", "field", "value", "from", "to", "include_lower", "include_upper"
    )
}
