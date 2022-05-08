package user

import scala.collection.mutable.ListBuffer

class user_object{
  case class rating(item:Int,rating:Int)
  case class movie(userid:Int,rating:Int)
  def userAsKey(inputFile:String):Map[Int,List[rating]]= {
    var A: Map[Int, List[rating]] = Map()
    var key:Int = 1
    while(key <= 943){
      A = A + (key -> user_helper(key,inputFile).toList)
      key = key + 1
    }
    A
  }
  def user_helper(key:Int,inputfile:String):ListBuffer[rating]= {
    var list = new ListBuffer[rating]()
    val matrix = io.Source.fromFile(inputfile).getLines()
    for (each <- matrix) {
      val eachLis = each.split("\\s+").toList
      if (eachLis.head.toInt == key) {
        val data = rating(eachLis(1).toInt, eachLis(2).toInt)
        list += data
      }
    }
    list
  }
  def movieAsKey(inputFile:String):Map[Int,Int]= {
    var A: Map[Int, Int] = Map()
    var key:Int = 1
    while(key <= 1682){
      val data_list = movie_helper(key, inputFile).toList
      val sum: Int = data_list.sum
      val ave: Int = sum / data_list.length
      A = A + (key -> ave)
      key = key + 1
    }
    A
  }
  def movie_helper(key:Int,inputfile:String):ListBuffer[Int]= {
    var list = new ListBuffer[Int]()
    val matrix = io.Source.fromFile(inputfile).getLines()
    for (each <- matrix) {
      val eachLis = each.split("\\s+").toList
      if (eachLis(1).toInt == key) {
        //val data = movie(eachLis.head.toInt, eachLis(2).toInt)
        list += eachLis(2).toInt
      }
    }
    list
  }
}

