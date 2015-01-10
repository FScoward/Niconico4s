package core.input

/**
 * Created by FScoward on 2015/01/11.
 */

import argonaut.Argonaut._
import argonaut.EncodeJson

trait Search
sealed case class Keyword(val title: String = "title", val description: String = "description", val tags: String = "tags") extends Search
sealed case class Tag(val tags_exact: String = "tags_exact") extends Search
object Search {
  implicit def SearchEncodeJson: EncodeJson[Search] = {
    EncodeJson((search: Search) => search match {
      case keyword: Keyword => keyword.title.asJson -->>: keyword.description.asJson -->>: keyword.tags.asJson -->>: jEmptyArray
      case tag: Tag => tag.tags_exact.asJson -->>: jEmptyArray
    })
  }
   
  /*
    implicit def SearchEncodeJson: EncodeJson[Keyword] = {
    EncodeJson(s => {
      s.title.asJson -->>: s.description.asJson -->>: s.tags.asJson -->>: jEmptyArray
    })
  }
  */
}
