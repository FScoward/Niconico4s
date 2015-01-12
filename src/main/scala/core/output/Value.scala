package core.output

/**
 * Created by FScoward on 2015/01/11.
 */
import argonaut.Argonaut._
import argonaut._
import core.input.Service

case class Value(_rowid: Int, cmsid: String, title: String, view_counter: Option[Int])
object Value {
  implicit def ValueCodecJson: CodecJson[Value] = {
    casecodec4(Value.apply, Value.unapply)("_rowid", "cmsid", "title", "view_counter")
  }
}
