package services

import models.{Book, Id}

import scala.collection.mutable

class BookDatabase  {
  private val books: mutable.Map[Id, Book] = mutable.Map.empty

  def add(a: Book): Book = {
    books += (a.id -> a)
    a
  }

  def get(id: Id): Book = books(id)

  def list: List[Book] = books.values.toList
}
