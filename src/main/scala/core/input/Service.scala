package core.input

/**
 * Created by FScoward on 2015/01/11.
 */

import argonaut.Argonaut._
import argonaut.{DecodeJson, CodecJson, EncodeJson}

sealed abstract class Service
object Service {
  case object video extends Service

  implicit def VideoEncodeJson: EncodeJson[Service] = {
    EncodeJson((s: Service) => jString(s.toString))
  }
}

