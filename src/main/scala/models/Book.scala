package models

// We could use value classes for title and author name also
case class Book(id: Id, title: String, authorName: String, genre: Genre, pages: Int)
