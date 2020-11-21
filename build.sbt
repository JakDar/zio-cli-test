val ZioVersion = "1.0.3"

resolvers += Resolver.sonatypeRepo("releases")
resolvers += Resolver.sonatypeRepo("snapshots")
scalaVersion := "2.13.4"

lazy val root = (project in file("."))
  .settings(
    organization := "ZIO",
    name := "zio-awesome-project",
    version := "0.0.1",
    scalaVersion := "2.13.4",
    maxErrors := 3,
    libraryDependencies ++= Seq(
      "dev.zio" %% "zio" % ZioVersion
    )
  )
// Refine scalac params from tpolecat
scalacOptions --= Seq(
  "-Xfatal-warnings"
)
