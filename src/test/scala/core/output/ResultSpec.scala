package core.output

/**
 * Created by FScoward on 2015/01/12.
 */
import argonaut._, Argonaut._
import scalaz._, Scalaz._
import org.specs2._
import org.specs2.specification._

class ResultSpec extends Specification {
  
  val jsonStr =
    """
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
    """
  val encodedJson = jsonStr.decodeOption[Result]
  
  def is =
    s2"""
         ${
      encodedJson must beSome
    }
        
        
      """
}
