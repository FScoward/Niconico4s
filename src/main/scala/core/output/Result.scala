package core.output

import argonaut.Argonaut._
import argonaut._

/**
 * Created by FScoward on 2015/01/11.
 */
case class Result(dqnid: String, `type`: String, values: Option[List[Value]], endofstream: String)
object Result {
  /*
  implicit def ResultCodecJson: CodecJson[Result] = {
    casecodec4(Result.apply, Result.unapply)("dqnid", "type", "values", "endofstream")
  }
  */
  implicit val DecodeResult: DecodeJson[Result] = {
    jdecode4(Result(_: String, _: String, _: Option[List[Value]], _: String))
  }
}
