package services
import models._
class LibraryService(db: BookDatabase, db2: AuthorDatabase) {


  def getBooksForGenre(genre: Genre): List[Book] = {
//    val allBook = db.list
//    var o = List.empty[models.Book]
//    for (book <- allBook){
//      if (book.genre == genre) {
//        o = o :+ book
//      }
//    }
//    o
    db.list.filter(_.genre == genre)
  }

  def getBooksForAuthor(authorName: String): List[Book] =
    db2.list.find(_.name == authorName) match {
      case None =>
        //Author not found
        List.empty
      case Some(author) =>
        db.list.filter(_.authorName == author.name)
    }

  def getAuthorTotalPages(authorName: String): Int =
    db2.list.find(_.name == authorName).map(author =>
      db.list.filter(_.authorName == author.name).map(_.pages).sum
    ).getOrElse(0)


  def addBookToAuthor(book: Book, authorName: String): Option[Book] =
    db2.getByName(authorName) match {
      case Some(_) =>
        db.add(book)
        Some(book)
      case None => None
    }

}

object LibraryService {
  def apply(db: BookDatabase, db2: AuthorDatabase) = new LibraryService(db, db2)
}