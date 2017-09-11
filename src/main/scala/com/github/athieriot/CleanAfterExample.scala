package com.github.athieriot

import com.mongodb.{MongoClient, ServerAddress}
import org.specs2.specification.AfterAll

import scala.collection.JavaConverters._

trait CleanAfterExample extends AfterAll { self: EmbedConnection =>

  lazy val mongoClient = new MongoClient(new ServerAddress(network.getServerAddress, network.getPort))

  override def afterAll() {
    mongoClient.listDatabaseNames().asScala.toList.map(mongoClient.getDatabase).foreach(_.drop)
  }

}