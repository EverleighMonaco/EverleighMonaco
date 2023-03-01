val scala3Version = "3.2.2"

lazy val root = project
  .in(file("."))
  .settings(
    name := "basel",
    version := "0.1.0-SNAPSHOT",
    scalaVersion := scala3Version,

    resolvers += "SynapseML" at "https://mmlspark.azureedge.net/maven",

    // look at this re: py
    libraryDependencies ++= Seq(
      "org.scalameta" %% "munit" % "0.7.29" % Test,
      ("dev.scalapy" %% "scalapy-core" % "0.5.3")
        .exclude("org.scala-lang.modules", "scala-collection-compat_3"),
     // test with this one tho https://github.com/doofin/scorch
      ("be.botkop" % "scorch_2.12" % "0.1.1")
        .exclude("com.typesafe.scala-logging", "scala-logging_2.12"),
    // kitchen sink it *ALL*
      ("io.github.pityka" %% "lamp-core" % "0.0.100")
        .exclude("org.typelevel", "cats-kernel_3"),
    // also please figure out which one of these to use bcuz this messy af
      // ("org.platanios" % "tensorflow_2.13" % "0.6.5")
        // .exclude("com.chuusai", "shapeless_2.13")
        // .exclude("org.scala-lang.modules", "scala-collection-compat_2.13")
        // .exclude("org.typelevel", "cats-kernel_2.13")
        // .exclude("org.typelevel", "cats-core_2.13"),
      ("com.microsoft.azure" % "synapseml_2.12" % "0.10.2"),
    // https://mvnrepository.com/artifact/com.microsoft.sarplus/sarplus
      "com.microsoft.sarplus" % "sarplus_2.12" % "0.6.6")
    // libraryDependencies += "com.spotify" %% "featran-core" % "0.8.0"
  )
