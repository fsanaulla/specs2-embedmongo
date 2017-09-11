package com.github.athieriot

import com.mongodb.casbah.Imports._
import org.bson.types.ObjectId
import salat.dao.SalatDAO
import salat.global._

case class Model(id: ObjectId = new ObjectId, name: String)

object Model extends SalatDAO[Model, ObjectId](collection = MongoConnection("localhost", 12345)("test")("model"))