package core.input

/**
 * Created by FScoward on 2015/01/11.
 */

import argonaut.Argonaut._
import argonaut.EncodeJson

/**
 * http://xerial.org/scala-cookbook/recipes/2012/06/29/enumeration/
 * */
sealed abstract class Join
object Join {
  case object cmsid extends Join
  case object title extends Join
  case object description extends Join
  case object tags extends Join
  case object start_time extends Join
  case object thumbnail_url extends Join
  case object view_counter extends Join
  case object comment_counter extends Join
  case object mylist_counter extends Join
  case object last_res_body extends Join
  case object length_seconds extends Join
  
  implicit def JoinValueEncodeJson: EncodeJson[Join] = {
    EncodeJson((jv: Join) => jString(jv.toString))
  }
}
