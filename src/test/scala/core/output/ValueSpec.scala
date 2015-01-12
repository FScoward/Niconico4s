package core.output

/**
 * Created by FScoward on 15/01/12.
 */
import argonaut._, Argonaut._
import scalaz._, Scalaz._
import org.specs2._
import org.specs2.specification._

class ValueSpec extends Specification {
  val jsonStr = """{"_rowid":0,"cmsid":"sm16091596","title":"高垣彩陽「melodia」CM"}"""

  val valueObj = Value(0, "sm16091596", "高垣彩陽「melodia」CM", None)
  val value: Option[Value] = jsonStr.decodeOption[Value]
  val decodedJson = Parse.decodeOption[Value](jsonStr)
  def  is =
    s2"""
      value must have value ${
        value must beSome 
      }
    
      decodedJson must have value
      ${
        decodedJson must beSome
      }
      
      ${
        value.get._rowid === decodedJson.get._rowid
    }
      """
}
