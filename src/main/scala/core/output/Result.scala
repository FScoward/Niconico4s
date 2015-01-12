package core.output

import argonaut.Argonaut._
import argonaut._

/**
 * Created by FScoward on 2015/01/11.
 */
case class Result(dqnid: String, `type`: String, values: Option[List[core.output.Value]], endofstream: Option[Boolean])
object Result {
  implicit def ResultCodecJson: CodecJson[Result] = {
    casecodec4(Result.apply, Result.unapply)("dqnid", "type", "values", "endofstream")
  }
}

/*
{"dqnid":"5300db54-2c94-4ef7-a2e3-6250d40e3dcb",
"type":"hits",
"values":[
  {"_rowid":0,"cmsid":"sm16091596","title":"高垣彩陽「melodia」CM"},
  {"_rowid":1,"cmsid":"sm16658710","title":"高垣彩陽「Meteor Light」CM"},
  {"_rowid":2,"cmsid":"sm11708199","title":"高垣彩陽で「HOT  LIMIT」"},
  {"_rowid":3,"cmsid":"sm18218163","title":"Defying Gravity　高垣彩陽　Acoustic Guitar Instrumental②"},
  {"_rowid":4,"cmsid":"sm25090278","title":"3 leaf clover 高垣彩陽 Acoustic Guitar Instrumental"},
  {"_rowid":5,"cmsid":"sm18113692","title":"Defying Gravity　高垣彩陽　Acoustic Guitar Instrumental"},
  {"_rowid":6,"cmsid":"sm16987956","title":"【CM】「Meteor Light」高垣彩陽"},
  {"_rowid":7,"cmsid":"sm17238022","title":"高垣彩陽　1歳9ヶ月のときの声"},
  {"_rowid":8,"cmsid":"sm12584621","title":"高垣彩陽さんをまとめたみた。　Part 4"},
  {"_rowid":9,"cmsid":"sm6765149","title":"高垣彩陽＠ルチャーさん　前半"}]
}
* */

/*
*
{"dqnid":"dqnid",
"type":"type",
"values":[
  {"_rowid":"_r","cmsid":"c","title":"t","view_counter":null}],
"endofstream":null
}

* */