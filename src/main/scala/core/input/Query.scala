package core.input

/**
 * Created by FScoward on 2015/01/11.
 */

import argonaut.Argonaut._
import argonaut.{Argonaut, EncodeJson}

case class Query(query: String,
                  service: List[Service],
                  search: Search,
                  join: List[Join],
                  filters: Option[List[Filter]],
                  sort_by: Option[SortBy],
                  order: Option[String],
                  from: Int = 0,
                  size: Int = 10,
                  issuer: String)

object Query{
  implicit def QueryEncodeJson: EncodeJson[Query] =
    Argonaut.jencode10L(Function.unlift(unapply))(
      "query", "service", "search", "join", "filters", "sort_by", "order", "from", "size", "issue"
    )
}

