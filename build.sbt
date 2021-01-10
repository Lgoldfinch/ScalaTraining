name := "ScalaWarehouse"

version := "0.1"

scalaVersion := "2.13.0"

libraryDependencies ++= Seq(
  "org.typelevel" %% "cats-core" % "2.1.0",
  "com.typesafe.akka" %% "akka-actor" % "2.6.1",
  "com.typesafe.play" %% "play-json" % "2.7.4",
  "commons-io" % "commons-io" % "2.6",
  "org.apache.commons" % "commons-lang3" % "3.9",
  "org.typelevel" %% "cats-core" % "2.0.0",
  "com.typesafe.play" %% "play-test" % "2.7.3"
)

scalacOptions ++= Seq(
//   "-Ypartial-unification",
  "-Xfatal-warnings",
   "-language:higherKinds")