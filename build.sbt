organization := "com.github.athieriot"

description := "Specs2 helper to configure a EmbedMongo based instance"

version := "0.8.0"

scalaVersion := "2.12.3"

crossScalaVersions := Seq("2.10.4", "2.11.8", scalaVersion.value)

resolvers ++= Seq(
  "Typesafe Releases" at "http://repo.typesafe.com/typesafe/releases/",
  "Typesafe Snapshots" at "http://repo.typesafe.com/typesafe/snapshots/",
  "Novus Snapshots" at "http://repo.novus.com/snapshots/",
  "Sonatype snapshots" at "http://oss.sonatype.org/content/repositories/snapshots/",
  "Sonatype releases" at "https://oss.sonatype.org/content/repositories/releases/"
)

libraryDependencies ++= Seq(
  "de.flapdoodle.embed" %  "de.flapdoodle.embed.mongo" % "2.0.0",
  "org.specs2"          %% "specs2-core"               % "3.9.5",
  "org.mongodb"         %% "casbah"                    % "3.1.1"    pomOnly(),
  "com.github.salat"    %% "salat"                     % "1.11.2"
)

parallelExecution in Test := false

publishMavenStyle := true
publishArtifact in Test := false
pomIncludeRepository := { _ => false }
pomExtra := (
      <url>http://github.com/athieriot/specs2-embedmongo</url>
      <licenses>
        <license>
          <name>Apache 2.0</name>
          <url>http://www.opensource.org/licenses/Apache-2.0</url>
          <distribution>repo</distribution>
        </license>
      </licenses>
      <scm>
        <url>git@github.com:athieriot/specs2-embedmongo.git</url>
        <connection>scm:git:git@github.com:athieriot/specs2-embedmongo.git</connection>
      </scm>
      <developers>
        <developer>
          <id>athieriot</id>
          <name>Aurélien Thieriot</name>
          <url>http://athieriot.github.com/resume</url>
        </developer>
      </developers>
    )
publishTo := {
  val nexus = "https://oss.sonatype.org/"
  if (version.value.trim.endsWith("SNAPSHOT"))
    Some("snapshots" at nexus + "content/repositories/snapshots")
  else
    Some("releases" at nexus + "service/local/staging/deploy/maven2")
}
