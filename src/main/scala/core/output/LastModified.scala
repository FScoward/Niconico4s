package core.output

import argonaut.CodecJson
import argonaut.Argonaut._

/**
 * Created by FScoward on 2015/01/12.
 */
case class LastModified(date: String)
object LastModified {
    implicit def ModifiedCodecJson: CodecJson[LastModified] = {
      casecodec1(LastModified.apply, LastModified.unapply)("last_modified")
    }
    
}
