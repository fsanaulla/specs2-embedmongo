package com.github.athieriot

import de.flapdoodle.embed.mongo._
import de.flapdoodle.embed.mongo.config._
import de.flapdoodle.embed.mongo.distribution._
import de.flapdoodle.embed.process.runtime.Network
import org.specs2.main.Arguments
import org.specs2.mutable.SpecificationLike
import org.specs2.specification.core.Fragments
import org.specs2.specification.create.FragmentsFactory
import org.specs2.specification.dsl.FragmentsDsl

trait EmbedConnection extends FragmentsFactory with FragmentsDsl { self: SpecificationLike =>

  isolated

  override def sequential: Arguments = args(isolated = false, sequential = true)

  override def isolated: Arguments = args(isolated = true, sequential = false)

  //Override this method to personalize testing port
  def embedConnectionPort(): Int = 12345

  //Override this method to personalize MongoDB version
  def embedMongoDBVersion(): Version.Main = { Version.Main.PRODUCTION }

  lazy val network = new Net(embedConnectionPort(), Network.localhostIsIPv6)

  lazy val mongodConfig: IMongodConfig = new MongodConfigBuilder()
    .version(embedMongoDBVersion())
    .net(network)
    .build

  lazy val runtime: MongodStarter = MongodStarter.getDefaultInstance

  lazy val mongodExecutable: MongodExecutable = runtime.prepare(mongodConfig)

  override def map(fs: => Fragments): Fragments = startMongo ^ fs ^ stoptMongo

  private def startMongo() = {
    fragmentFactory.example("Start Mongo", { mongodExecutable.start; success })
  }

  private def stoptMongo() = {
    fragmentFactory.example("Stop Mongo", { mongodExecutable.stop(); success })
  }
}
