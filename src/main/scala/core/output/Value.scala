package core.output

/**
 * Created by FScoward on 2015/01/11.
 */
import argonaut.Argonaut._
import argonaut._

case class Value(_rowid: String, cmsid: String, title: String, view_counter: Int)
object Value {
  /*
  implicit def ValueCodecJson: CodecJson[Value] = {
//    casecodec4[Value](Value.apply, Value.unapply)("_rowid", "cmsid", "title", "view_counter")
  }
  */
  
  implicit val DecodeValue: DecodeJson[Value] = {
    jdecode4(Value(_: String, _: String, _: String, _:Int))
  }
}

/*
{"dqnid":"c0676eea-cc77-4317-b442-d626c5f34558","type":"hits",

"values":[{
"_rowid":0,
"cmsid":"sm13208019",
"title":"【DIVA 2nd】　鏡音八八花合戦　【EDIT PV】",
"view_counter":9999},

{"_rowid":1,
"cmsid":"sm12215733",
"title":"メイドイン俺でミニゲーム　その５",
"view_counter":9997},

{"_rowid":2,"cmsid":"sm3495465","title":"【初音ミクオリジナル】～プレゼント～【Independence Free】","view_counter":9997}]}


{"dqnid":"c0676eea-cc77-4317-b442-d626c5f34558","type":"stats","values":[{"_rowid":0,"service":"video","total":213353}]}
{"dqnid":"c0676eea-cc77-4317-b442-d626c5f34558","endofstream":true,"type":"hits"}
{"dqnid":"c0676eea-cc77-4317-b442-d626c5f34558","endofstream":true,"type":"stats"}
 */