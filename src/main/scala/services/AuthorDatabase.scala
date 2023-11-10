package services

import models.{Author, Id}

import scala.collection.mutable

class AuthorDatabase {

  val as: mutable.Map[Id, Author] = mutable.Map.empty

  def add(a: Author): Author = {
    as += (a.id -> a)
    a
  }

  def get(id: Id): Author = getOpt(id).get

  def getOpt(id: Id): Option[Author] = as.get(id)

  def list: List[Author] = as.values.toList

  def getByName(name: String): Option[Author] = list.filter(_.name == name).headOption

}
