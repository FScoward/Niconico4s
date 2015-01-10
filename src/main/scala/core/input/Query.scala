package core.input

/**
 * Created by FScoward on 2015/01/11.
 */

import argonaut.Argonaut._
import argonaut.EncodeJson

case class Query(query: String,
                  service: List[Service],
                  search: Search,
                  join: List[Join],
                  filters: Option[List[Filter]],
                  sort_by: Option[String],
                  order: Option[String],
                  from: Option[Int],
                  size: Option[Int],
                  issue: String)

object Query{
  implicit def QueryEncodeJson: EncodeJson[Query] = {
    jencode10L((q: Query) => (q.query, q.service, q.search, q.join, q.filters, q.sort_by, q.order, q.from, q.size, q.issue))("query", "service", "search", "join", "filters", "sort_by", "order", "from", "size", "issue")
  }
}

