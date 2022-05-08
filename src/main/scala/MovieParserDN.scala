import io.StdIn._
import io.Source
import java.io.File
import java.io.FileWriter
import java.io.PrintWriter
import scala.collection.mutable.ListBuffer

//Returns a list of movie entries
object MovieParserDN {
  def parseMovieFile(path: String): List[MovieEntryDN] = {
    // Initialize a list buffer
    var movieEntriesBuffer = new ListBuffer[MovieEntryDN]()
    // Read in the file
    val lines: List[String] = Source.fromFile(path, "windows-1250").getLines.toList
    // For each line, extract the information
    for (line <- lines) {
      //println(line)
      var str = new StringBuilder("")
      var splitList = new ListBuffer[String]()
      for (c <- line) {
        if (c != '|') {
          str += c
        }
        else {
          if (!str.isEmpty && !str.startsWith("http")) {
            splitList += str.toString
          }
          str = new StringBuilder("")
        }
      }
      //println(splitList.toList)
      var genresBuffer = new ListBuffer[Int]()
      for (i <- 0 until splitList.length-4) {
        if (splitList(i + 3) == "1") {
          genresBuffer += i
        }
      }
      val id = splitList(0).toInt
      val title = splitList(1)
      val year = splitList(2)
      //println(genresBuffer)
      val movieEntry = new MovieEntryDN(id, title, year, genresBuffer.toList)
      movieEntriesBuffer += movieEntry
    }
    println(movieEntriesBuffer)
    return movieEntriesBuffer.toList
  }

  //Returns a map of genres with a number as the key and the corresponding genre as the value
  def parseGenreFile(path: String): Map[Int, String] = {
    var genresMap: Map[Int, String] = Map()
    val lines: List[String] = Source.fromFile(path).getLines.toList
    var count = 0
    for (line <- lines) {
      //println(line)
      if (!line.isEmpty) {
        var str = new StringBuilder("")
        var splitList = new ListBuffer[String]()
        for (c <- line) {
          //if not the separating character or a digit, build the string for the genre
          if (c != '|') {
            str += c
          }
          else {
            splitList += str.toString
            genresMap += (count -> str.toString)
          }
        }
        //println("count: " + count)
        //println(splitList.toList)
        count += 1
      }
    }

    for ((k,s) <- genresMap) printf(k + "->" + s + "\n")

    return genresMap
  }
}