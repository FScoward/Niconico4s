package core.input

import argonaut.Argonaut._
import argonaut.EncodeJson
/**
 * Created by FScoward on 2015/01/12.
 */
sealed abstract class SortBy
object SortBy {
  case object last_comment_time extends SortBy
  case object view_counter extends SortBy
  case object start_time extends SortBy
  case object mylist_counter extends SortBy
  case object comment_counter extends SortBy
  case object length_seconds extends SortBy
  
  implicit def SortByEncodeJson: EncodeJson[SortBy] = {
    EncodeJson((sortBy: SortBy) => jString(sortBy.toString))
  }

}
