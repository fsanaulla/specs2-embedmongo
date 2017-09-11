package com.github.athieriot

import org.specs2.mutable.Specification

class EmbedConnectionSpec extends Specification with EmbedConnection {
  sequential

  "Embed database" should {
    "be able to save a Model I" in {
      Model.save(Model(name = "test"))
      Model.count() must be_==(1)
    }

    "be able to save a Model II" in {
      Model.save(Model(name = "test"))
      Model.count() must be be_== (2)
    }

    "be able to save a Model III" in {
      Model.save(Model(name = "test"))
      Model.count() must be be_== (3)
    }

    "be able to save a Model IV" in {
      Model.save(Model(name = "test"))
      Model.count() must be be_== (4)
    }

    "be able to save a Model V" in {
      Model.save(Model(name = "test"))
      Model.count() must be be_== (5)
    }
  }
}

