package services
import models._
class LibraryService(bookDb: BookDatabase, authorsDb: AuthorDatabase) {


  def getBooksForGenre(genre: Genre): List[Book] = {
//    val allBook = db.list
//    var o = List.empty[models.Book]
//    for (book <- allBook){
//      if (book.genre == genre) {
//        o = o :+ book
//      }
//    }
//    o
    bookDb.list.filter(_.genre == genre)
  }

  def getBooksForAuthor(authorName: String): List[Book] =
    authorsDb.list.find(_.name == authorName) match {
      case None =>
        //Author not found
        List.empty
      case Some(author) =>
        bookDb.list.filter(_.authorName == author.name)
    }

  def getAuthorTotalPages(authorName: String): Int = {
    // First line is not strictly necessary - tests show you return 0 if author doesn't exist
    // Code could be improved to surface the error that the author doesn't exist
    authorsDb.list.find(_.name == authorName).map(author =>
      bookDb.list.filter(_.authorName == author.name).map(_.pages).sum
    ).getOrElse(0)
  }


  def addBookToAuthor(book: Book, authorName: String): Option[Book] =
    authorsDb.getByName(authorName) match {
      case Some(_) =>
        bookDb.add(book)
        Some(book)
      case None => None
    }

}

object LibraryService {
  def apply(books: BookDatabase, authors: AuthorDatabase) = new LibraryService(books, authors)
}