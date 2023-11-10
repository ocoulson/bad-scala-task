import models.{Author, Book, Genre, Id}
import org.scalatest.matchers.must.Matchers
import org.scalatest.wordspec.AnyWordSpec
import services.{AuthorDatabase, BookDatabase, LibraryService}

class Test extends AnyWordSpec with Matchers {


  val books = new BookDatabase
  val authors = new AuthorDatabase
  val libService = new LibraryService(books, authors)

  val a1 = Author(Id("a1"), "Jack Smith")
  val a2 = Author(Id("a2"), "George Woods")

  authors.add(a1)
  authors.add(a2)

  private val actionComedy: Genre = Genre("Action Comedy")
  private val horror: Genre = Genre("Horror")
  private val romance: Genre = Genre("Romance")
  private val book1: Book = Book(Id("id1"), "foo", a1.name, actionComedy, 10)
  private val book2: Book = Book(Id("id2"), "foo", a2.name, horror, 100)
  private val book3: Book = Book(Id("id3"), "bar", a1.name, horror, 140)
  books.add(book1)
  books.add(book2)
  books.add(book3)

  "getBooksForGenre" must {

    "return an empty list if the genre doesn't exist" in {
      libService.getBooksForGenre(Genre("made up")) mustBe List()
    }

    "get the correct books" in {
      libService.getBooksForGenre(horror) mustBe List(
        book2, book3
      )

      libService.getBooksForGenre(actionComedy) mustBe List(
        book1
      )
    }
  }
  "getBooksForAuthor" must {

    "return an empty list if the author doesn't exist" in {
      libService.getBooksForAuthor("made up ") mustBe List()
    }
    "get the correct books" in {
      libService.getBooksForAuthor(a1.name) mustBe List(
       book1, book3
      )

      libService.getBooksForAuthor(a2.name) mustBe List(
        book2
      )
    }
  }

  "getAuthorsTotalPages" must {
    "return 0 if no books" in  {
      libService.getAuthorTotalPages("noone") mustBe 0
    }

    "return the expected count" in {
      libService.getAuthorTotalPages(a1.name) mustBe List(book1, book3).map(_.pages).sum
    }
  }

  "addBookToAuthor" must {
    "return None if author doesn't exist" in {
      val a3 = Author(Id("a3"), "Nemo")
      libService.addBookToAuthor(Book(Id("id4"), "title", a3.name, romance, 1), a3.name) mustBe None
    }
    "return the book as a Some(book) if the author exists" in {
      val book = Book(Id("id4"), "title", a2.name, romance, 1)
      libService.addBookToAuthor(book, a2.name) mustBe Some(book)

      books.get(Id("id4")) mustBe book
    }
  }

  "services.AuthorDatabase" must {
    "getByName" must {
      "return None if the name doesn't exist" in {
        authors.getByName("no name") mustBe None
      }
    }
  }
}
