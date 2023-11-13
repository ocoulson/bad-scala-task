package services

import models.{Author, Id}

import scala.collection.mutable

class AuthorDatabase {

  private val authors: mutable.Map[Id, Author] = mutable.Map.empty

  def add(a: Author): Author = {
    authors += (a.id -> a)
    a
  }

  def get(id: Id): Author = getOpt(id).get

  def getOpt(id: Id): Option[Author] = authors.get(id)

  def list: List[Author] = authors.values.toList

  def getByName(name: String): Option[Author] = list.filter(_.name == name).headOption

}
